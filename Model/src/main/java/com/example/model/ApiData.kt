package com.example.model

data class ApiData(
    val text: String = "",
    val meanings: List<Meanings> = arrayListOf()
)