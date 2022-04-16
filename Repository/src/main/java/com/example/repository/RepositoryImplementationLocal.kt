package com.example.repository

import com.example.model.ApiData
import com.example.model.AppState

class RepositoryImplementationLocal(private val dataSource: com.example.repository.DataSourceLocal<List<com.example.model.ApiData>>) :
    RepositoryLocal<List<com.example.model.ApiData>> {

    override suspend fun getData(word: String): List<com.example.model.ApiData> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: com.example.model.AppState) {
        dataSource.saveToDB(appState)
    }
}