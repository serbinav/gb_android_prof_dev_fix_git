package com.example.mytranslator

import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.retrofit.Meanings
import com.example.mytranslator.view_model.AppState

fun parseSearchResults(data: AppState): AppState {
    val newSearchResults = arrayListOf<ApiData>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
    }

    return AppState.Success(newSearchResults)
}

private fun parseResult(dataModel: ApiData, newDataModels: ArrayList<ApiData>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(ApiData(dataModel.text, newMeanings))
        }
    }
}