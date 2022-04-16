package com.example.repository.utils

import com.example.model.ApiData
import com.example.model.AppState
import com.example.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<ApiData> {
    val searchResult = ArrayList<ApiData>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(ApiData(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}