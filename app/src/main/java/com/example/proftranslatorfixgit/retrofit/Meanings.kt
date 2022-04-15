package com.example.proftranslatorfixgit.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meanings(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null,
) : Parcelable