package com.example.proftranslatorfixgit.history

import com.example.core.Provider
import com.example.model.ApiDataDTO
import com.example.model.AppState
import com.example.repository.RepositoryLocal
import com.example.utils.mapSearchResultToResult

class HistoryProvider(
private val repositoryLocal: RepositoryLocal<List<ApiDataDTO>>,
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
    }
}