package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.AppState

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