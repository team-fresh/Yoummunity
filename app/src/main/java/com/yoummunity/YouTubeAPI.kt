package com.yoummunity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YouTubeAPI {
    @GET("v3/commentThreads")
    fun query(
        // For more info, see [https://developers.google.com/youtube/v3/docs/commentThreads/list]   // API key
        @Query("key") key: String = "AIzaSyCEk4fukdGonbEO5Xwl98vY4M0W_av37w8",
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 100,             // specifies the maximum number of items that should be returned in the result set.
        @Query("order") order: String = "relevance",            // specifies the order in which the API response should list comment threads.
        @Query("videoId") videoId: String,
        @Query("pageToken") pageToken: String?
    ): Call<Data>
}