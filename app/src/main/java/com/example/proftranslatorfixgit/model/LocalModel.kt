package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.room.HistoryDao
import com.example.mytranslator.utils.convertDataModelSuccessToEntity
import com.example.mytranslator.utils.mapHistoryEntityToSearchResult
import com.example.mytranslator.view_model.AppState

class LocalModel(private val historyDao: HistoryDao) :
    DataSourceLocal<List<ApiData>> {
    override suspend fun getData(word: String): List<ApiData> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}