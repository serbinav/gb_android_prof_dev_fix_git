package com.example.repository

import com.example.model.ApiDataDTO
import com.example.repository.retrofit.ApiFactory

class RemoteModel : DataSource<List<ApiDataDTO>> {

    private val skyEngApi = ApiFactory.create()

    override suspend fun getData(word: String): List<ApiDataDTO> {
        return skyEngApi.search(word).await()
    }
}