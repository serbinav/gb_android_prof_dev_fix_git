package com.example.proftranslatorfixgit.model

import com.example.proftranslatorfixgit.retrofit.ApiData

class RepositoryImplementation(private val dataSource: DataSource<List<ApiData>>) :
    Repository<List<ApiData>> {

    override suspend fun getData(word: String): List<ApiData> {
        return dataSource.getData(word)
    }
}