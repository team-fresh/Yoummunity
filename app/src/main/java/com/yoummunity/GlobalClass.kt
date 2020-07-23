package com.yoummunity

import android.app.Application
import android.graphics.Bitmap
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

/*
 * 전역변수 사용하기 위한 클래스
 */

class GlobalClass {
    companion object {
        var webView: CustomView? = null
        var videoId: String? = null
        var authorProfileImages = mutableListOf<Bitmap>()
        var authors = mutableListOf<String>()
        var comments = mutableListOf<String>()
    }
}