package com.example.model

import com.google.gson.annotations.SerializedName

class MeaningsDTO(
    @SerializedName("translation")
    val translation: TranslationDTO?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
)