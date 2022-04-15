package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.view_model.AppState

class HistoryProvider(
private val repositoryLocal: RepositoryLocal<List<ApiData>>,
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryLocal.getData(word))
    }
}