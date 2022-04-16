package com.example.repository

import com.example.model.ApiData
import com.example.repository.retrofit.ApiFactory

class RemoteModel : DataSource<List<ApiData>> {

    private val skyEngApi = ApiFactory.create()

    override suspend fun getData(word: String): List<ApiData> {
        return skyEngApi.search(word).await()
    }
}