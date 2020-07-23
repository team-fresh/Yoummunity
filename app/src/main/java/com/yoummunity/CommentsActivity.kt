package com.yoummunity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.list_item_comment.*

class CommentsActivity : Activity() {
    private var text: String = ""
    private var textList = mutableListOf<CharSequence>()
    private var arrayAdapter: ArrayAdapter<CharSequence>? = null
    private var listCommentsView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        listCommentsView = list_comments
        arrayAdapter = ArrayAdapter<CharSequence>(this, R.layout.list_item_comment, textList)

        window.attributes.gravity =
            Gravity.BOTTOM + Gravity.RIGHT  // set position of dialog on window
        window.attributes.x = 0
        window.attributes.y = 30
        window.setDimAmount(0.3f)                   // set background dim of dialog
    }

    override fun onResume() {
        super.onResume()
//        var textView = textview_comment
//        var data = intent.getStringExtra("data")
        for (i in GlobalClass.comments.indices) {
            text =
                "<b>" + GlobalClass.authors[i] + "</b><br>" + GlobalClass.comments[i]
            // text: String in HTML
            textList.add(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }

        arrayAdapter?.notifyDataSetChanged() // notify arrayAdapter that data has changed
        listCommentsView?.adapter = arrayAdapter
//        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
