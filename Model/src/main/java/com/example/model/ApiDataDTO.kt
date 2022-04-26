package com.example.model

import com.google.gson.annotations.SerializedName

class ApiDataDTO(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<MeaningsDTO?>?
)