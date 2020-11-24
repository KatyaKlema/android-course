package com.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
var articles : List<Article> = emptyList()

fun logCursor(c: Cursor?) {
    if (c != null) {
        if (c.moveToFirst()) {
            var str: String
            do {
                str = ""
                for (cn in c.getColumnNames()) {
                    str = str + cn + " = " + c.getString(c.getColumnIndex(cn)) + "; "
                }
                Log.d(LOG_TAG, str)
            } while (c.moveToNext())
        }
    } else Log.d(LOG_TAG, "Cursor is null")
}
val LOG_TAG = "-----------LOGS"

class MainActivity : AppCompatActivity(),  Communicator {
    private var mIsDualPane = false;
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        // init our database
        val dbHelper = DBHelper(this, null)
        dbHelper.clearAllArticles()
        dbHelper.addArticles(DataProvider.data)

        // create articleStorage
        var articlesStorage = ArticlesStorage(dbHelper)
        articlesStorage.init()
        articles = articlesStorage.articles
//        var articles = dbHelper.getAllArticles()
//        Log.println(10, null, "!!!!!!!!" + articles.toString())
//        logCursor(articles)
        for(article in articles){
            Log.println(10, null, "article - " + article)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.article_fragment)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE


    }

    override fun displayDetails(articleId: Int) {
        if(mIsDualPane){
            // If we are in Tablet
            val articleFragment = supportFragmentManager.findFragmentById(R.id.article_fragment) as ArticleFragment?
            articleFragment?.displayDetails(articleId)
        }
        else{
            // When we are in Smart phone
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("articleId", articleId.toString())
            startActivity(intent)
        }
    }
}