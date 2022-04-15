package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.AppState

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<ApiData>>) :
    RepositoryLocal<List<ApiData>> {

    override suspend fun getData(word: String): List<ApiData> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}