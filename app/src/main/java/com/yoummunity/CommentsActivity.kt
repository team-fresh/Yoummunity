package com.yoummunity

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : Activity() {
    var text: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        window.attributes.gravity =
            Gravity.BOTTOM + Gravity.RIGHT  // set position of dialog on window
        window.attributes.x = 0
        window.attributes.y = 30
        window.setDimAmount(0.3f)                   // set background dim of dialog
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
            text += "<b>" + GlobalClass.authors[i] + "</b><br>" + GlobalClass.comments[i] + "<br><br>"
            // text: String in HTML
        }
        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
