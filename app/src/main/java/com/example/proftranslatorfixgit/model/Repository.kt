package com.example.mytranslator.model

interface Repository<T> {

    suspend fun getData(word: String): T

}