package com.yoummunity

import android.app.Activity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jsoup.Jsoup

class CommentViewModel(var activity: Activity, var url: String?) {
    fun getComment() {
        var strings = url?.split("watch?v=")
        var videoId =
            if (strings!!.size!! >= 2) {
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
            val document = Jsoup.connect(url).get()

            val queue = Volley.newRequestQueue(activity)
            val stringRequest = StringRequest(
                Request.Method.GET, request,
                Response.Listener<String> { response ->
                    println("good ${response.substring(0, 5000)}")
                },
                Response.ErrorListener {
                    println("error")
                })
            queue.add(stringRequest)
        }
    }
}