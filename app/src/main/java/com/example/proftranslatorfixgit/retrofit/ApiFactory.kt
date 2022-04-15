package com.example.mytranslator.retrofit

import com.example.mytranslator.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object ApiFactory {

    private val gson: Gson =
        GsonBuilder()
            .create()

    private val skyEngApi: SearchApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_LOCATIONS)
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SearchApi::class.java)
    }

    fun create(): SearchApi = skyEngApi
}