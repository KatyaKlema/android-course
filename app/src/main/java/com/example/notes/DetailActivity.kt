package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve data coming from MainActivity.java
        val text = intent.getStringExtra("text")
        val photoId = intent.getStringExtra("photoId")

        // Pass the data to FragmentB to display it
        val articleFragment = supportFragmentManager.findFragmentById(R.id.article_fragment) as ArticleFragment?
        if (text != null) {
            if (photoId != null) {
                articleFragment?.displayDetails(text, photoId.toInt())
            }
        }
    }
}


