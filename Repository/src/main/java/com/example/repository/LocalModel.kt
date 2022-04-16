package com.example.repository

import com.example.repository.utils.convertDataModelSuccessToEntity
import com.example.repository.utils.mapHistoryEntityToSearchResult

class LocalModel(private val historyDao: com.example.repository.room.HistoryDao) :
    com.example.repository.DataSourceLocal<List<com.example.model.ApiData>> {
    override suspend fun getData(word: String): List<com.example.model.ApiData> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: com.example.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}