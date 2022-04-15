package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData

class RepositoryImplementation(private val dataSource: DataSource<List<ApiData>>) :
    Repository<List<ApiData>> {

    override suspend fun getData(word: String): List<ApiData> {
        return dataSource.getData(word)
    }
}