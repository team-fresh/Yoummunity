package com.yoummunity

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.net.URL

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
            GlobalClass.authorProfileImages = mutableListOf<Bitmap>()
            GlobalClass.authors = mutableListOf<String>()
            GlobalClass.comments = mutableListOf<String>()

            var response =
                RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                    .execute()

            while (response.isSuccessful && videoId == GlobalClass.videoId) {
                val data = response.body()
                val pageToken = data?.nextPageToken
                println("[pageToken] $pageToken")

                total += data!!.items.size
                /**
                 * 댓글 총 개수보다 적게 불러오는 오류 -> 실제 display되는 댓글 수와는 일치
                 */
                println("size: ${data.items.size}")
                for (item in data.items) {
                    if (videoId != GlobalClass.videoId) break
                    Log.d("VIEW_MODEL", "${GlobalClass.videoId} ### $videoId")

                    val snippet = item.snippet.topLevelComment.snippet
                    val authorProfileImage =
                        BitmapFactory.decodeStream(URL(snippet.authorProfileImageUrl).openStream())
                    val author = snippet.authorDisplayName
                    val comment = snippet.textOriginal

                    GlobalClass.authorProfileImages.add(authorProfileImage)
                    GlobalClass.authors.add(author)
                    GlobalClass.comments.add(comment)
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