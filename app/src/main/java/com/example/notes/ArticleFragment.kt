package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    fun displayDetails(articleId : Int) {
        txvText.text = articles[articleId].text
        imgArticle.setImageResource(articles[articleId].photoId)
    }

}