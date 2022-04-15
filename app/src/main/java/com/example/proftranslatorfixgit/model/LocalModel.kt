package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.room.HistoryDao
import com.example.proftranslatorfixgit.utils.convertDataModelSuccessToEntity
import com.example.proftranslatorfixgit.utils.mapHistoryEntityToSearchResult
import com.example.proftranslatorfixgit.view_model.AppState

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