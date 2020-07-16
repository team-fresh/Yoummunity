package com.yoummunity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var globalClass = GlobalClass(webview_main)
        var chromeClient = ChromeClient(this)
        val actionBar = supportActionBar
        actionBar?.hide()

        globalClass.webView.webChromeClient = chromeClient
        globalClass.webView.webViewClient = WebViewClient()    // prevent a new window from opening

        var mWebSettings = globalClass.webView.settings
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

        globalClass.webView.loadUrl(globalClass.urlYouTube)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        // TODO: 영상 재생 중 화면 회전해도 영상 크기 유지하는 문제 해결
        super.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        // TODO: 두 번 누를 때 앱 종료 구현하기
        var globalClass = GlobalClass(webview_main)

        if (globalClass.webView.originalUrl.equals(globalClass.urlYouTube)) {
            // TODO: originalUrl에서 null 발생 가능 -> onPageFinished 메소드 override해서 해결하기
            super.onBackPressed()
            // Toast.makeText(this, globalClass.mWebView.originalUrl, Toast.LENGTH_SHORT).show()
        } else if (globalClass.webView.canGoBack()) {
            globalClass.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}
