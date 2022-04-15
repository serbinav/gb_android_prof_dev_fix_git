package com.example.mytranslator.model

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.retrofit.Meanings
import com.example.mytranslator.retrofit.Translation

class LocalModel : DataSource<List<ApiData>> {
    private var data: List<ApiData> = arrayListOf(
        ApiData(text = "school", arrayListOf(Meanings(Translation("школа")))),
        ApiData(text = "schooling", arrayListOf(Meanings(Translation("образование")))),
        ApiData(text = "schoolhouse", arrayListOf(Meanings(Translation("здание школы"))))
    )

    override suspend fun getData(word: String): List<ApiData> {
        return data
    }
}