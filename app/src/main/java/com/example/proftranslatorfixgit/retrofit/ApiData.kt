package com.example.mytranslator.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiData(
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("meanings")
    val meanings: List<Meanings>? = arrayListOf()
) : Parcelable