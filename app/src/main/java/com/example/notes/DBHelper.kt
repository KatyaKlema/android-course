package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_TEXT + " TEXT, " +
                COLUMN_DATE + " TEXT," +
                COLUMN_PHOTO_ID + " INTEGER" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addArticle(article: Article) {
        val values = ContentValues()
        values.put(COLUMN_ID, article.articleId) // <-- maybe it is not right
        values.put(COLUMN_TITLE, article.title)
        values.put(COLUMN_TEXT, article.text)
        values.put(COLUMN_DATE, article.date)
        values.put(COLUMN_PHOTO_ID, article.photoId)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllArticles(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    fun clearAllArticles(){
        val db = this.writableDatabase
        db.execSQL("delete from "+ TABLE_NAME);
    }
    fun addArticles(articles: List<Article>){
        for(article in articles){
            addArticle(article)
        }
    }
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "articles_table.db"
        val TABLE_NAME = "articles"
        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "article_title"
        val COLUMN_TEXT = "article_text"
        val COLUMN_DATE = "article_date"
        val COLUMN_PHOTO_ID = "article_photo_id"
    }
}