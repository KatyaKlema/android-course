package com.example.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface CellClickListener {
    fun onCellClickListener(activity : AppCompatActivity)
}

class MainActivity : AppCompatActivity(), CellClickListener {
    class Article(text_ : String, date_ : String,  photoId_ : Int, activity_ : AppCompatActivity, articleId_ : Int) {
        var text : String = text_
        var photoId : Int = photoId_
        var date : String = date_
        var activity : AppCompatActivity = activity_
        var articleId : Int =  articleId_
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val articles = ArrayList<Article>()
        articles.add(Article("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "09.12.2018", R.drawable.dreem, Article0(), 0))
        articles.add(Article("Implement this interface in activity or fragment wherever you wish to pass control. In this example this interface is implemented in MainActivity. Pass the reference of activity or fragment that implements CellClickListener to adapter like the line highlighted below is passing reference of activity in Adapter.", "24.07.2010", R.drawable.dreem, Article1(), 1))
        articles.add(Article("Recycler view uses several components to display data in a list format. The parent component is RecyclerView and it is implemented in activity or fragment. A recycler view is filled by views provided by LayoutManager (LinearLayoutManager or GridLayoutManager). A view is represented by view holder objects, data for each cell is set in view holder. We can access a view holder object in Adapter class. …", "12.11.2011", R.drawable.dreem, Article2(), 2))


        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MyAdapter(articles, this)
        rv.adapter = adapter
        rv.setOnClickListener {
            val intent = Intent(this, ReadActivity :: class.java)
            startActivity(intent)
        }
    }
    override fun onCellClickListener(activity : AppCompatActivity) {
        val intent = Intent(this, activity :: class.java)
            startActivity(intent)
    }
}