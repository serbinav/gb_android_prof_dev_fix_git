package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.view_model.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}