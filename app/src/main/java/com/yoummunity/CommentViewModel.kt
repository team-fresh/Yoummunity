package com.yoummunity

import android.app.Activity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONException
import org.json.JSONObject
import org.jsoup.Jsoup

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

        var request = "${activity.getString(R.string.request_comment)}$videoId"
        GlobalClass.videoId = videoId
        println(videoId)

        GlobalScope.async {
            Jsoup.connect(url).get()
            var isPageLeft = true
            var nextPageToken: String? = null
            val queue = Volley.newRequestQueue(activity)

            val initialRequest = StringRequest(
                Request.Method.GET, request,
                Response.Listener<String> { response ->
                    val jsonObject = JSONObject(response)
                    try {
                        nextPageToken = jsonObject.getString("nextPageToken")
                    } catch (e: JSONException) {
                        // there is no nextPageToken
                        isPageLeft = false
                    }

                    val items = jsonObject.getJSONArray("items")
                    for (i in 0 until items.length()) {
                        val snippet = items.getJSONObject(i).getJSONObject("snippet")
                            .getJSONObject("topLevelComment").getJSONObject("snippet")
                        val textOriginal = snippet.getString("textOriginal")
                        val authorDisplayName = snippet.getString("authorDisplayName")
                        println("$textOriginal ### $authorDisplayName")
                    }
                },
                Response.ErrorListener {
                    println("error")
                })
            queue.add(initialRequest)

            while (isPageLeft) {
                // TODO: 무한루프 해결
                request =
                    "${activity.getString(R.string.request_comment)}$videoId&pageToken=$nextPageToken"
                var stringRequest = StringRequest(
                    Request.Method.GET, request,
                    Response.Listener<String> { response ->
                        val jsonObject = JSONObject(response)
                        try {
                            println("try")
                            nextPageToken = jsonObject.getString("nextPageToken")
                        } catch (e: JSONException) {
                            // there is no nextPageToken
                            println("catch")
                            isPageLeft = false
                        }

                        val items = jsonObject.getJSONArray("items")
                        for (i in 0 until items.length()) {
                            val snippet = items.getJSONObject(i).getJSONObject("snippet")
                                .getJSONObject("topLevelComment").getJSONObject("snippet")
                            val textOriginal = snippet.getString("textOriginal")
                            val authorDisplayName = snippet.getString("authorDisplayName")
                            println("$textOriginal ### $authorDisplayName")
                        }
                    },
                    Response.ErrorListener {
                        println("error")
                    })
                queue.add(stringRequest)
            }
        }
    }
}