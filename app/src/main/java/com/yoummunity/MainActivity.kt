package com.yoummunity

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.yoummunity.GlobalClass.Companion.authors
import com.yoummunity.GlobalClass.Companion.videoId
import com.yoummunity.GlobalClass.Companion.webView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val backPressHandler = BackPressHandler(this)
    private var rotatingClockwiseButton: Animation? = null
    private var rotatingCounterClockwiseButton: Animation? = null
    private var openingButton: Animation? = null
    private var closingButton: Animation? = null
    private var isButtonOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = webview_main

        val actionBar = supportActionBar
        actionBar?.hide()

        val buttonDefault = button_default
        val button1 = button_1
        val button2 = button_2

        buttonDefault.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)

        rotatingClockwiseButton =
            AnimationUtils.loadAnimation(applicationContext, R.anim.button_rotate_clockwise)
        rotatingCounterClockwiseButton =
            AnimationUtils.loadAnimation(applicationContext, R.anim.button_rotate_counterclockwise)
        openingButton = AnimationUtils.loadAnimation(applicationContext, R.anim.button_open)
        closingButton = AnimationUtils.loadAnimation(applicationContext, R.anim.button_close)

        webView!!.webChromeClient = ChromeClient(this)
        webView!!.webViewClient =
            CustomViewClient(this)            // prevent a new window from opening

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

    override fun onClick(view: View?) {
        // floating button click event
        var id = view?.id
        when (id) {
            R.id.button_default -> {
                anim()
            }
            R.id.button_1 -> {
                anim()

                if (webView?.url?.equals(getString(R.string.url_youtube))!!) {
                    // no video selected
                    return
                }
                if (GlobalClass.isEmpty) {
                    // video has no comment
                    Toast.makeText(this, getString(R.string.guide_no_comment), Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                if (GlobalClass.authors.isEmpty()) {
                    // loading comments
                    return
                }

                val intent = Intent(this, CommentsActivity::class.java)
                // intent.putExtra("data", "test")
                startActivity(intent)
            }
            R.id.button_2 -> {
                anim()
                // TODO: profile 메뉴 버튼 클릭 이벤트
            }
        }
    }

    private fun anim() {
        // floating button animation
        if (isButtonOpened) {
            button_default.startAnimation(rotatingCounterClockwiseButton)
            button_1.startAnimation(closingButton)
            button_2.startAnimation(closingButton)
            button_1.isClickable = false
            button_2.isClickable = false
            isButtonOpened = false
        } else {
            button_default.startAnimation(rotatingClockwiseButton)
            button_1.startAnimation(openingButton)
            button_2.startAnimation(openingButton)
            button_1.isClickable = true
            button_2.isClickable = true
            isButtonOpened = true
        }
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
