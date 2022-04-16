package com.example.repository.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Deferred<List<com.example.model.ApiData>>
}