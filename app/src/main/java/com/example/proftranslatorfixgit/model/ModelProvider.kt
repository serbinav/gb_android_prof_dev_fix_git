package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.AppState

class ModelProvider(
    private val repositoryRemote: Repository<List<ApiData>>,
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryRemote.getData(word))
    }
}