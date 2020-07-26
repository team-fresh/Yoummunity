package com.yoummunity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_emoji.*

class EmojiActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)

        val EmojiList = arrayListOf(
            Emoji(R.drawable.person,"person"),
            Emoji(R.drawable.girl,"girl"),
            Emoji(R.drawable.doctor,"doctor"),
            Emoji(R.drawable.chef,"chef"),
            Emoji(R.drawable.child,"child")
        )

        recyclerView.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = EmojiAdapter(EmojiList)




    }

}