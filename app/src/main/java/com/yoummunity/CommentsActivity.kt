package com.yoummunity

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : Activity() {
    var text: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
    }

    override fun onResume() {
        super.onResume()
        var textView = textview_comment
//        var data = intent.getStringExtra("data")
        if (GlobalClass.authors.isEmpty() or GlobalClass.comments.isEmpty()) {
            textView.text = getString(R.string.guide_no_comment)
            return
        }
        for (i in GlobalClass.comments.indices) {
            text += GlobalClass.authors[i] + "\n" + GlobalClass.comments[i] + "\n\n"
        }
        textView.text = text
    }
}
