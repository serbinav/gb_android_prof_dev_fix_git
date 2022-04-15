package com.example.mytranslator.view_model

import com.example.mytranslator.retrofit.ApiData

sealed class AppState {

    data class Success(val data: List<ApiData>?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()
}