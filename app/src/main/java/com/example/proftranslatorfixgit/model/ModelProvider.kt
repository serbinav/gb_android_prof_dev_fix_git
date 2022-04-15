package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.view_model.AppState

class ModelProvider(
    private val repositoryRemote: Repository<List<ApiData>>,
    private val repositoryLocal: RepositoryLocal<List<ApiData>>
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        val appState = AppState.Success(repositoryRemote.getData(word))
        repositoryLocal.saveToDB(appState)
        return appState
    }
}