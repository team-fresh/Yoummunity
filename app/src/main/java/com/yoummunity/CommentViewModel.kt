package com.yoummunity

import android.app.Activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewModel(var activity: Activity, var url: String?) {
    fun getComment() {
        var strings = url?.split("watch?v=")
        var videoId =
            if (strings!!.size >= 2) {
                strings[1]
            } else {
                null
            }

        when (videoId) {
            GlobalClass.videoId, null -> return
        }
        GlobalClass.videoId = videoId
        println(videoId)

        var pageToken: String? = null

        val response =
            RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                .enqueue(object : Callback<Data> {
                    override fun onFailure(call: Call<Data>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                        val data = response.body()
                        val pageToken = data?.nextPageToken
                        println("[pageToken] $pageToken")

                        println("size: ${data!!.items.size}")
                        for (item in data!!.items) {
                            val snippet = item.snippet.topLevelComment.snippet
                            val comment = snippet.textOriginal
                            val author = snippet.authorDisplayName

                            println("$comment ### $author")
                        }
                    }
                })
//        TODO: 모든 댓글 받아오기
    }
}