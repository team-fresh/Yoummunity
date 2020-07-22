package com.yoummunity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YouTubeAPI {
    @GET("v3/commentThreads")
    fun query(
        @Query("key") key: String = "AIzaSyCEk4fukdGonbEO5Xwl98vY4M0W_av37w8",
        @Query("part") part: String = "snippet",
        @Query("order") order: String = "relevance",
        @Query("videoId") videoId: String,
        @Query("pageToken") pageToken: String?
    ): Call<Data>
}