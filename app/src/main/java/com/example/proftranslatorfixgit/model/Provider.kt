package com.example.proftranslatorfixgit.model

interface Provider<T> {

    suspend fun getData(word: String): T

}