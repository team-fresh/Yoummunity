package com.yoummunity

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
    }

    override fun onResume() {
        super.onResume()
        var textView = textview_comment
        var text: String = ""
//        var data = intent.getStringExtra("data")
        if (GlobalClass.authors.isEmpty() or GlobalClass.comments.isEmpty()) {
            return
        }
        for (i in GlobalClass.comments.indices) {
            text += GlobalClass.authors[i] + "\n" + GlobalClass.comments[i] + "\n\n"
        }
        textView.text = text
    }
}
