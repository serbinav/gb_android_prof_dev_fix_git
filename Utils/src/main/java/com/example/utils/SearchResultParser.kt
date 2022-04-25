package com.example.utils

import com.example.model.*

fun mapSearchResultToResult(searchResults: List<ApiDataDTO>):
        List<ApiData> {
    return searchResults.map { searchResult ->
        var meanings: List<Meanings> = listOf()
        searchResult.meanings?.let {
            meanings = it.map { meaningsDto ->
                Meanings(
                    Translation(
                        meaningsDto?.translation?.translation ?: ""
                    ),
                    meaningsDto?.imageUrl ?: ""
                )
            }
        }
        ApiData(searchResult.text ?: "", meanings)
    }
}

fun parseOnlineSearchResults(data: AppState): AppState {
    return AppState.Success(mapResult(data, true))
}

fun parseLocalSearchResults(data: AppState): AppState {
    return AppState.Success(mapResult(data, false))
}

private fun mapResult(
    data: AppState,
    isOnline: Boolean
): List<ApiData> {
    val newSearchResults = arrayListOf<ApiData>()
    when (data) {
        is AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    data: AppState.Success,
    isOnline: Boolean,
    newSearchDataModels: ArrayList<ApiData>
) {
    val searchDataModels: List<ApiData> = data.data as List<ApiData>
    if (searchDataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in searchDataModels) {
                parseOnlineResult(searchResult, newSearchDataModels)
            }
        } else {
            for (searchResult in searchDataModels) {
                newSearchDataModels.add(
                    ApiData(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(
    searchDataModel: ApiData,
    newSearchDataModels: ArrayList<ApiData>
) {
    if (searchDataModel.text.isNotBlank() && searchDataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        newMeanings.addAll(searchDataModel.meanings.filter {
            it.translation.translation.isNotBlank()
        })
        if (newMeanings.isNotEmpty()) {
            newSearchDataModels.add(
                ApiData(
                    searchDataModel.text,
                    newMeanings
                )
            )
        }
    }
}

fun convertMeaningsToSingleString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation.translation, ", ")
        } else {
            meaning.translation.translation
        }
    }
    return meaningsSeparatedByComma
}
