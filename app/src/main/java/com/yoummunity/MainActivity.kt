package com.yoummunity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.yoummunity.GlobalClass.Companion.videoId
import com.yoummunity.GlobalClass.Companion.webView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = webview_main

        val actionBar = supportActionBar
        actionBar?.hide()

        webView!!.webChromeClient = ChromeClient(this)
        webView!!.webViewClient = WebViewClient()               // prevent a new window from opening

        var mWebSettings = webView!!.settings
        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)           // whether new windows are allowed
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false

        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(false)
        mWebSettings.builtInZoomControls = false

        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true                   // whether to enable DOM storage API

        webView!!.loadUrl(getString(R.string.url_youtube))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        // TODO: 영상 재생 중 화면 회전해도 영상 크기 유지하는 문제 해결
        super.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        backPressHandler.onBackPressed()
        println("[ID] ${videoId}")
    }
}
