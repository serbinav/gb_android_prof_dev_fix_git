package com.example.mytranslator.model

interface Provider<T> {

    suspend fun getData(word: String): T

}