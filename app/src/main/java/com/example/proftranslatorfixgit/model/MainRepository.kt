package com.example.mytranslator.model

import io.reactivex.rxjava3.core.Single

interface MainRepository<T> {

    fun getData(word: String): Single<List<T>>

}