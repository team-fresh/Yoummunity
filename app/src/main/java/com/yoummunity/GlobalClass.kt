package com.yoummunity

import android.app.Application
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*

class GlobalClass(
    var webView: WebView,
    val urlYouTube: String = "https://www.youtube.com/"
) : Application() {

}