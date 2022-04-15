package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.retrofit.ApiFactory

class RemoteModel : DataSource<List<ApiData>> {

    private val skyEngApi = ApiFactory.create()

    override suspend fun getData(word: String): List<ApiData> {
        return skyEngApi.search(word).await()
    }
}