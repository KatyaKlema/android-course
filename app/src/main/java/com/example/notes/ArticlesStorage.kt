package com.example.notes

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.ContactsContract
import androidx.core.content.contentValuesOf
import kotlinx.coroutines.*

fun convertCursorToArticleList(c: Cursor?) : List<Article> {
    var articles: MutableList<Article> = mutableListOf()
    try{
        if (c != null) {
            while (c.moveToNext()) {
                var articleTitle: String = c.getString(c.getColumnIndex(DBHelper.COLUMN_TITLE))
                var articleText: String = c.getString(c.getColumnIndex(DBHelper.COLUMN_TEXT))
                var articleId: Int = c.getInt(c.getColumnIndex(DBHelper.COLUMN_ID))
                var articlePhotoId: Int = c.getInt(c.getColumnIndex(DBHelper.COLUMN_PHOTO_ID))
                var articleDate: String = c.getString(c.getColumnIndex(DBHelper.COLUMN_DATE))
                var article = Article(articleText, articleTitle, articleDate, articlePhotoId, articleId)
                articles.add(article)
            }
        }

    } finally {
        c?.close()
    }

    return articles.toList()
}
class ArticlesStorage(var databaseHolder: DatabaseHolder){
    suspend fun getAllArticles(): List<Article> = withContext(Dispatchers.IO) {
        var article_cursor = databaseHolder.dbHelper.getAllArticles()
        var articles: List<Article>
        try {
            articles = convertCursorToArticleList(article_cursor)
        } finally {
            article_cursor?.close()
        }
        return@withContext articles
    }
    suspend fun getArticleById(articleId: Int): Article = withContext(Dispatchers.IO){
        var article_cursor = databaseHolder.dbHelper.getArticleById(articleId)
        var article: Article
        try {
            article = convertCursorToArticleList(article_cursor)[0]
        } finally {
            article_cursor?.close()
        }
        return@withContext article
    }
}