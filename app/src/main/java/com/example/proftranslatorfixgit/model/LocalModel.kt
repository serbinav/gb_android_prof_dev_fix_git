package com.example.mytranslator.model

import io.reactivex.rxjava3.core.Single

class LocalModel : MainRepository<WordTranslate>{
    private var data: List<WordTranslate> = arrayListOf(
        WordTranslate("school", "школа"),
        WordTranslate("schooling", "образование"),
        WordTranslate("schoolhouse", "здание школы")
    )

    override fun getData(word: String): Single<List<WordTranslate>> {
        return Single.just(data)
    }
}