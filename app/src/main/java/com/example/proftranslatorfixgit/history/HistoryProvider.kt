package com.example.proftranslatorfixgit.history

import com.example.core.Provider
import com.example.model.ApiData
import com.example.model.AppState
import com.example.repository.RepositoryLocal

class HistoryProvider(
private val repositoryLocal: RepositoryLocal<List<ApiData>>,
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryLocal.getData(word))
    }
}