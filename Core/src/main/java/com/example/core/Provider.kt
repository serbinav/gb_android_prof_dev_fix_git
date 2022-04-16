package com.example.core

interface Provider<T> {

    suspend fun getData(word: String): T

}