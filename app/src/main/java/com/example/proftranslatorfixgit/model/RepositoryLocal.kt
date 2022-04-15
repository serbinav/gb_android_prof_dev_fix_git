package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.view_model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}