package com.yoummunity

import android.app.Activity
import android.widget.Toast
import com.yoummunity.GlobalClass.Companion.webView

class BackPressHandler(var activity: Activity) {
    private var lastPressedTime: Long = -1
    private val TIME_INTERVAL: Long = 1000
    private var toast: Toast? = null

    fun onBackPressed() {
        if (webView!!.originalUrl.equals(activity.getString(R.string.url_youtube))) {
            // TODO: originalUrl에서 null 발생 가능 -> onPageFinished 메소드 override해서 해결하기
        } else if (webView!!.canGoBack()) {
            webView!!.goBack()
            return
        }

        if (System.currentTimeMillis() > lastPressedTime + TIME_INTERVAL) {
            lastPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(
                activity,
                activity.getString(R.string.guide_back),
                Toast.LENGTH_SHORT
            )
            toast!!.show()
        } else {
            activity.finish()
            toast!!.cancel()
        }
    }
}