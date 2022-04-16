package com.example.repository

import com.example.model.ApiData

class RepositoryImplementation(private val dataSource: com.example.repository.DataSource<List<com.example.model.ApiData>>) :
    Repository<List<com.example.model.ApiData>> {

    override suspend fun getData(word: String): List<com.example.model.ApiData> {
        return dataSource.getData(word)
    }
}