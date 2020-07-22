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
            GlobalClass.authors = mutableListOf<String>()
            GlobalClass.comments = mutableListOf<String>()

            var response =
                RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                    .execute()
            while (response.isSuccessful) {
                val data = response.body()
                val pageToken = data?.nextPageToken
                println("[pageToken] $pageToken")

                total += data!!.items.size
                /**
                 * 댓글 총 개수보다 적게 불러오는 오류 -> 실제 display되는 댓글 수와는 일치
                 */
                println("size: ${data!!.items.size}")
                for (item in data!!.items) {
                    val snippet = item.snippet.topLevelComment.snippet
                    val comment = snippet.textOriginal
                    val author = snippet.authorDisplayName

                    GlobalClass.authors.add(author)
                    GlobalClass.comments.add(comment)

                    println("$comment ### $author")
                }

                if (pageToken == null) {
                    println(GlobalClass.authors)
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