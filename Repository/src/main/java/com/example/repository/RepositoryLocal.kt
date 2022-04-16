package com.example.repository

import com.example.model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: com.example.model.AppState)
}