package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter(private val context: Context?, private var list: List<Article>) :
    RecyclerView.Adapter<RecyclerAdapter.ArticleViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = list[position]
        holder.setData(current, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = list.size

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        lateinit var current: Article

        fun setData(current: Article, position: Int) {
            itemView.tvText.text = current.title
            itemView.img_row.setImageResource(current.photoId)
            itemView.date.text = current.date
            this.pos = position
            this.current = current
        }

        fun setListeners() {

            itemView.setOnClickListener {
                val myCommunicator = context as Communicator
                myCommunicator.displayDetails(current.articleId)
            }
        }
    }
}
