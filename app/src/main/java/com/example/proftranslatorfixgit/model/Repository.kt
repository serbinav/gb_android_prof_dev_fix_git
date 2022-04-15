package com.example.proftranslatorfixgit.model

interface Repository<T> {

    suspend fun getData(word: String): T

}