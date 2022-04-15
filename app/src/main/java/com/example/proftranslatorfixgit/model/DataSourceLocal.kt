package com.example.mytranslator.model

import com.example.mytranslator.view_model.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}