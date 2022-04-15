package com.example.mytranslator.model

interface DataSource<T> {

    suspend fun getData(word: String): T

}