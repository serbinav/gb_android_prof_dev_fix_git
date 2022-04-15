package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiFactory
import com.example.mytranslator.retrofit.Data
import io.reactivex.rxjava3.core.Single

class RemoteModel : MainRepository<Data> {

    private val skyEngApi = ApiFactory.create()

    override fun getData(word: String): Single<List<Data>> {
        return skyEngApi.search(word)
    }
}