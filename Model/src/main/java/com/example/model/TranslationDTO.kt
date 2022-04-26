package com.example.model

import com.google.gson.annotations.SerializedName

class TranslationDTO(
    @SerializedName("text")
    val translation: String?
)