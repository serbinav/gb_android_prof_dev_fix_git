package com.example.repository

import com.example.model.ApiDataDTO
import com.example.model.AppState

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<ApiDataDTO>>) :
    RepositoryLocal<List<ApiDataDTO>> {

    override suspend fun getData(word: String): List<ApiDataDTO> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}