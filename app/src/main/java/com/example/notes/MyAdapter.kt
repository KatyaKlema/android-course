package com.example.notes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class MyAdapter(val articles: ArrayList<MainActivity.Article>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<MyAdapter.ArticleViewHolder>(){
    class ArticleViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            val cv = itemView.findViewById<View>(R.id.cardView) as CardView
            val text = itemView.findViewById<View>(R.id.textView3) as TextView
            val date = itemView.findViewById<View>(R.id.textView4) as TextView
            val photo = itemView.findViewById<View>(R.id.imageView) as ImageView
        }

    override fun getItemCount(): Int {
        return articles.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return ArticleViewHolder(v)
    }

    override fun onBindViewHolder(articleViewHolder: ArticleViewHolder, position: Int) {
        articleViewHolder.text.text = articles[position].text
        articleViewHolder.date.text = articles[position].date
        articleViewHolder.photo.setImageResource(articles[position].photoId)
        articleViewHolder.cv.setOnClickListener {
            cellClickListener.onCellClickListener(articles[position].activity)
        }
    }
}