package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve data coming from MainActivity.java
        val articleId = intent.getStringExtra("articleId")?.toInt()
        // Pass the data to FragmentB to display it
        val articleFragment = supportFragmentManager.findFragmentById(R.id.article_fragment) as ArticleFragment?
        if (articleId != null) {
            articleFragment?.displayDetails(articleId)
        }
    }
}


