package com.example.repository.utils

import com.example.model.ApiDataDTO
import com.example.model.AppState
import com.example.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<ApiDataDTO> {
    val searchResult = ArrayList<ApiDataDTO>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(ApiDataDTO(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text, null)
            }
        }
        else -> null
    }
}