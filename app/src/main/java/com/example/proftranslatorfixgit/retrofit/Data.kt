package com.example.proftranslatorfixgit.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("meanings")
    val meanings: List<Meanings>? = arrayListOf()
) : Parcelable