package com.yoummunity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_emoji_item.view.*

class EmojiAdapter(val EmojiList: ArrayList<Emoji>, val type: Boolean) :
    RecyclerView.Adapter<EmojiAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): EmojiAdapter.CustomViewHolder {
        val view: View
        view = if (type) {
            LayoutInflater.from(parent.context).inflate(R.layout.list_emoji_item, parent, false)
        } else
            LayoutInflater.from(parent.context).inflate(R.layout.list_tag_item, parent, false)
        return CustomViewHolder(view, type)
    }

    override fun getItemCount(): Int {
        return EmojiList.size
    }

    override fun onBindViewHolder(holder: EmojiAdapter.CustomViewHolder, position: Int) {
        holder.image.setImageResource(EmojiList.get(position).image)
    }

    class CustomViewHolder(itemView: View, type: Boolean) : RecyclerView.ViewHolder(itemView) {

        val image: ImageButton = if (type)
            itemView.findViewById<ImageButton>(R.id.imagebtn)
        else
            itemView.findViewById<ImageButton>(R.id.tagbtn)
    }
}
