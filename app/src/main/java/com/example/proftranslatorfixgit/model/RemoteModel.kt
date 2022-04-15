package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.retrofit.ApiFactory

class RemoteModel : DataSource<List<ApiData>> {

    private val skyEngApi = ApiFactory.create()

    override suspend fun getData(word: String): List<ApiData> {
        return skyEngApi.search(word).await()
    }
}