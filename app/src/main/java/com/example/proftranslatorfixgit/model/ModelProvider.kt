package com.example.proftranslatorfixgit.model

import com.example.model.ApiData
import com.example.model.AppState

class ModelProvider(
    private val repositoryRemote: com.example.repository.Repository<List<com.example.model.ApiData>>,
    private val repositoryLocal: com.example.repository.RepositoryLocal<List<com.example.model.ApiData>>
) : com.example.core.Provider<com.example.model.AppState> {

    override suspend fun getData(word: String): com.example.model.AppState {
        val appState = com.example.model.AppState.Success(repositoryRemote.getData(word))
        repositoryLocal.saveToDB(appState)
        return appState
    }
}