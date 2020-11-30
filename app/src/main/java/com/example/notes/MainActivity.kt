package com.example.notes

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

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

class MainActivity : AppCompatActivity(), Communicator {
    companion object {
        private const val DETAIL_ARTICLE_BACK_STACK_NAME = "detail"
        lateinit var articlesStorage: ArticlesStorage
    }

    var detailArticleFrameLayoutId = R.id.content_frame_layout

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        // init our database
        var dbHelper : DBHelper = DBHelper(this, null)

        // create articleStorage
        articlesStorage = ArticlesStorage(DatabaseHolder(dbHelper))
//        var articles = dbHelper.getAllArticles()
//        Log.println(10, null, "!!!!!!!!" + articles.toString())
//        logCursor(articles)

        var articles = articlesStorage.getAllArticles()
        for (article in articles) {
            Log.println(10, null, "article - " + article)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_layout, ArticleListFragment())
                .commit()
        }

        if (findViewById<View>(R.id.sub_content_frame_layout) != null) {
            detailArticleFrameLayoutId = R.id.sub_content_frame_layout
        }
    }

    override fun displayDetails(articleId: Int) {
        if (supportFragmentManager.findFragmentByTag(ArticleFragment.TAG) != null) {
            supportFragmentManager
                .popBackStack(DETAIL_ARTICLE_BACK_STACK_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                detailArticleFrameLayoutId,
                ArticleFragment.newInstance(articleId),
                ArticleFragment.TAG
            )
            .addToBackStack(DETAIL_ARTICLE_BACK_STACK_NAME)
            .commit()
    }
}
