package com.example.notes

import android.content.Context
import android.database.Cursor
import android.util.Log

fun convertCursorToArticleList(c: Cursor?) : List<Article> {
    var articles: MutableList<Article> = mutableListOf()
    if (c != null) {
        if (c.moveToFirst()) {
            var articleTitle: String = ""
            var articleText: String = ""
            var articleId: Int = -1
            var articlePhotoId: Int = -1
            var articleDate: String = ""
            do {
                for (cn in c.getColumnNames()) {
                    if(cn == "_id"){
                        articleId = c.getString(c.getColumnIndex(cn)).toInt()
                    }
                    else if(cn == "article_title"){
                        articleTitle = c.getString(c.getColumnIndex(cn))
                    }
                    else if(cn == "article_text"){
                        articleText = c.getString(c.getColumnIndex(cn))
                    }
                    else if(cn == "article_date"){
                        articleDate = c.getString(c.getColumnIndex(cn))
                    }
                    else if(cn == "article_photo_id"){
                        articlePhotoId = c.getString(c.getColumnIndex(cn)).toInt()
                    }
                    if(articleId != -1 && articleTitle != "" && articleText != "" && articleDate != "" && articlePhotoId != -1){
                        articles.add(Article(articleText, articleTitle, articleDate, articlePhotoId, articleId))
                        articleTitle = ""
                        articleText = ""
                        articleId = -1
                        articlePhotoId = -1
                        articleDate = ""
                    }
                }

            } while (c.moveToNext())
        }
    }
    return articles.toList()
}
class ArticlesStorage(var dbHelper : DBHelper){
    lateinit var articles: List<Article>
    fun init(){
        var article_cursor = dbHelper.getAllArticles()
        articles = convertCursorToArticleList(article_cursor)
    }
}