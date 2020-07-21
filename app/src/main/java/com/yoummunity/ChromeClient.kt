package com.yoummunity

import android.R.color.black
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import java.util.jar.Attributes

/*
 * YouTube 동영상 전체화면 재생을 위한 클래스
 */

class ChromeClient(var activity: Activity) : WebChromeClient() {
    private var customView: View? = null
    private var originalOrientation: Int = 0
    private var fullscreenContainer: FullScreenHolder? = null
    private var customViewCallback: CustomViewCallback? = null

    class FullScreenHolder : FrameLayout {
        constructor(
            context: Context
        ) : this(context, null)

        constructor(
            context: Context,
            attrs: AttributeSet?
        ) : this(context, attrs, 0)

        constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
        ) : super(context, attrs, defStyleAttr) {
            setBackgroundColor(context.resources.getColor(black))
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            return true
            // return super.onTouchEvent(event)
        }
    }

    override fun onPermissionRequest(request: PermissionRequest?) {     //
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            request?.grant(request.resources)
        }
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {

        result?.confirm()
        return super.onJsAlert(view, url, message, result)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (customView != null) {
            callback?.onCustomViewHidden()
            return
        }
        originalOrientation = activity.requestedOrientation

        fullscreenContainer = FullScreenHolder(activity)
        fullscreenContainer!!.addView(view, ViewGroup.LayoutParams.MATCH_PARENT)

        var decorView: FrameLayout = activity.window.decorView as FrameLayout
        decorView.addView(fullscreenContainer, ViewGroup.LayoutParams.MATCH_PARENT)

        customView = view
        customViewCallback = callback
        activity.requestedOrientation = originalOrientation

        // super.onShowCustomView(view, callback)
    }

    override fun onHideCustomView() {
        if (customView == null) {
            return
        }
        activity.requestedOrientation = originalOrientation

        var decorView: FrameLayout = activity.window.decorView as FrameLayout
        decorView.removeView(fullscreenContainer)

        fullscreenContainer = null
        customView = null
        customViewCallback!!.onCustomViewHidden()

        // super.onHideCustomView()
    }
}