package com.example.mytranslator.model

import com.example.mytranslator.view_model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}