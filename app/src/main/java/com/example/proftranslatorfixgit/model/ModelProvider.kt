package com.example.proftranslatorfixgit.model

import com.example.core.Provider
import com.example.model.ApiDataDTO
import com.example.model.AppState
import com.example.repository.Repository
import com.example.repository.RepositoryLocal
import com.example.utils.mapSearchResultToResult

class ModelProvider(
    private val repositoryRemote: Repository<List<ApiDataDTO>>,
    private val repositoryLocal: RepositoryLocal<List<ApiDataDTO>>
) : Provider<AppState> {

    override suspend fun getData(word: String): AppState {
        val appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
        repositoryLocal.saveToDB(appState)
        return appState
    }
}