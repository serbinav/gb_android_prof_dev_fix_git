package com.example.repository

import com.example.model.ApiDataDTO

class RepositoryImplementation(private val dataSource: DataSource<List<ApiDataDTO>>) :
    Repository<List<ApiDataDTO>> {

    override suspend fun getData(word: String): List<ApiDataDTO> {
        return dataSource.getData(word)
    }
}