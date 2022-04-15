package com.example.mytranslator.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Translation(
    @SerializedName("text")
    val translation: String? = null,
) : Parcelable