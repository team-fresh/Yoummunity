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

        var pageToken: String? = null

        GlobalScope.launch {
            GlobalClass.authorProfileImages = mutableListOf<Bitmap>()
            GlobalClass.authors = mutableListOf<String>()
            GlobalClass.comments = mutableListOf<String>()
            GlobalClass.videoIds = mutableListOf<String>()

            var response =
                RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                    .execute()

            while (response.isSuccessful && videoId == GlobalClass.videoId) {
                val data = response.body()
                val pageToken = data?.nextPageToken
                /**
                 * 댓글 총 개수보다 적게 불러오는 오류 -> 실제 display되는 댓글 수와는 일치
                 */
                GlobalClass.isEmpty = data!!.items.isNullOrEmpty()

                for (i in data.items.indices) {
                    val item = data.items[i]
                    if (videoId != GlobalClass.videoId) break

                    val snippet = item.snippet.topLevelComment.snippet
                    val authorProfileImage =
                        BitmapFactory.decodeStream(URL(snippet.authorProfileImageUrl).openStream())
                    val author = snippet.authorDisplayName
                    val comment = snippet.textOriginal

                    GlobalClass.authorProfileImages.add(authorProfileImage)
                    GlobalClass.authors.add(author)
                    GlobalClass.comments.add(comment)
                    GlobalClass.videoIds.add(videoId)
                }
                if (pageToken == null) break

                response =
                    RetrofitClient.getService().query(videoId = videoId!!, pageToken = pageToken)
                        .execute()
            }
        }
    }
}