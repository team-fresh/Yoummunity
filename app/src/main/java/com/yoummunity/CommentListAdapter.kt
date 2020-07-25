package com.yoummunity

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CommentListAdapter : BaseAdapter() {
    private var commentItems = ArrayList<CommentListItem>()

    fun addItem(authorImage: Drawable, authorName: CharSequence, comment: CharSequence) {
        val commentItem = CommentListItem()

        commentItem.authorImage = authorImage
        commentItem.authorName = authorName
        commentItem.comment = comment

        commentItems.add(commentItem)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val commentItem = commentItems[position]

        var view = convertView
        val context = parent?.context

        if (view == null) {
            val inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item_comment, parent, false)
        }

        val authorImage: ImageView = view!!.findViewById(R.id.image_author)
        val authorName: TextView = view.findViewById(R.id.text_author)
        val comment: TextView = view.findViewById(R.id.item_comment)

        authorImage.setImageDrawable(commentItem.authorImage)
        authorName.setText(commentItem.authorName)
        comment.setText(commentItem.comment)

        return view
    }

    override fun getItem(position: Int): Any {
        return commentItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return commentItems.size
    }
}