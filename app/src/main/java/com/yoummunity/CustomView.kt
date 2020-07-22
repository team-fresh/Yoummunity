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
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr)