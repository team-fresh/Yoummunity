package com.yoummunity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): YouTubeAPI = retrofit.create(YouTubeAPI::class.java)
}