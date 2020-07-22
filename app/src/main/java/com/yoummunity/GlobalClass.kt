package com.yoummunity

import android.app.Application
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*

/*
 * 전역변수 사용하기 위한 클래스
 */

class GlobalClass {
    companion object {
        var webView: CustomView? = null
        var videoId: String? = null
        var authors = mutableListOf<String>()
        var comments = mutableListOf<String>()
    }
}