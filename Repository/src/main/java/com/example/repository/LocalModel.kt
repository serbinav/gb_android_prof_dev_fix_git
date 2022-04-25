package com.example.repository

import com.example.model.ApiDataDTO
import com.example.model.AppState
import com.example.repository.room.HistoryDao
import com.example.repository.utils.convertDataModelSuccessToEntity
import com.example.repository.utils.mapHistoryEntityToSearchResult

class LocalModel(private val historyDao: HistoryDao) :
    DataSourceLocal<List<ApiDataDTO>> {
    override suspend fun getData(word: String): List<ApiDataDTO> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}