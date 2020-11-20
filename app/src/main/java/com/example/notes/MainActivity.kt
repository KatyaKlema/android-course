package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),  Communicator {
    private var mIsDualPane = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.article_fragment)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(text: String, photoId: Int) {
        if(mIsDualPane){
            // If we are in Tablet
            val articleFragment = supportFragmentManager.findFragmentById(R.id.article_fragment) as ArticleFragment?
            articleFragment?.displayDetails(text, photoId)
        }
        else{
            // When we are in Smart phone
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("text", text)
            intent.putExtra("photoId", photoId.toString())
            startActivity(intent)
        }
    }
}