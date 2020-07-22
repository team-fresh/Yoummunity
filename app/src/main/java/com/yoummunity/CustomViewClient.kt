package com.yoummunity

import android.app.Activity
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomViewClient(var activity: Activity) : WebViewClient() {
    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        CommentViewModel(
            activity,
            GlobalClass.webView?.url
        ).getComment()         // web crawling with retrofit2, coroutine
        super.doUpdateVisitedHistory(view, url, isReload)
    }
}