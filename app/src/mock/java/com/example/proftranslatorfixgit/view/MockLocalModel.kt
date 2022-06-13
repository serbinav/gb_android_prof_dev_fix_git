package com.example.proftranslatorfixgit.view

import com.example.model.ApiDataDTO
import com.example.model.AppState
import com.example.repository.DataSourceLocal
import com.example.repository.room.HistoryEntity
import com.example.repository.utils.convertDataModelSuccessToEntity
import com.example.repository.utils.mapHistoryEntityToSearchResult

class MockLocalModel : DataSourceLocal<List<ApiDataDTO>> {
    override suspend fun getData(word: String): List<ApiDataDTO> {
        return mapHistoryEntityToSearchResult(
            listOf(
                HistoryEntity(
                    word = "my",
                    description = "мой"
                )
            )
        )
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
        }
    }
}