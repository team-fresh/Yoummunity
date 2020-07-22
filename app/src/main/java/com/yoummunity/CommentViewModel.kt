package com.yoummunity

import android.app.Activity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        var total = 0

        GlobalScope.launch {
            var response =
                RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                    .execute()
            while (response.isSuccessful) {
                val data = response.body()
                val pageToken = data?.nextPageToken
                println("[pageToken] $pageToken")

                total = total + data!!.items.size
                // TODO: 댓글 총 개수보다 적게 불러오는 오류 해결
                println("size: ${data!!.items.size}")
                for (item in data!!.items) {
                    val snippet = item.snippet.topLevelComment.snippet
                    val comment = snippet.textOriginal
                    val author = snippet.authorDisplayName

                    println("$comment ### $author")
                }

                if (pageToken == null) {
                    println("total: $total")
                    break
                }

                response =
                    RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                        .execute()
            }
        }
    }
}