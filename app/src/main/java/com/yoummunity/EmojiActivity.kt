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

        val TagList = arrayListOf(
            Emoji(R.drawable.man,"person"),
            Emoji(R.drawable.smile,"doctor"),
            Emoji(R.drawable.baby,"doctor")
         )
        tag_recycler.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        tag_recycler.setHasFixedSize(true)
        tag_recycler.adapter = EmojiAdapter(TagList,false)

        emoji_recycler.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        emoji_recycler.setHasFixedSize(true)
        emoji_recycler.adapter = EmojiAdapter(EmojiList,true)




    }

}