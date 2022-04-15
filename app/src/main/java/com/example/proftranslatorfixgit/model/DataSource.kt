package com.example.proftranslatorfixgit.model

interface DataSource<T> {

    suspend fun getData(word: String): T

}