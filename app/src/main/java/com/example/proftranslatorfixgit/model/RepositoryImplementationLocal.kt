package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.view_model.AppState

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<ApiData>>) :
    RepositoryLocal<List<ApiData>> {

    override suspend fun getData(word: String): List<ApiData> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}