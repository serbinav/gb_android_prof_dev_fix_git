package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.AppState

class HistoryProvider(
private val repositoryLocal: RepositoryLocal<List<ApiData>>,
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryLocal.getData(word))
    }
}