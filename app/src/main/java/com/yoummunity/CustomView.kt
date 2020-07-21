package com.yoummunity

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

class CustomView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : WebView(context, attrs, defStyleAttr) {

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if(url != null) {
            GlobalClass.videoId
        }

        if (!url.equals(context.getString(com.yoummunity.R.string.url_youtube))) {
            CommentViewModel(context as Activity, url).getComment()         // web crawling with async, coroutine
        }
    }
}