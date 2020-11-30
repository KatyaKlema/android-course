package com.example.notes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.concurrent.locks.ReentrantLock

class DatabaseHolder(var dbHelper: DBHelper) {
    private lateinit var sqLiteDatabase : SQLiteDatabase
    private var databaseOpenCloserBalance : Int = 0
    private lateinit var reentrantLock: ReentrantLock

    fun init(){
        dbHelper.clearAllArticles()
        dbHelper.addArticles(DataProvider.data)
    }
    fun open(): SQLiteDatabase{
        try{
            reentrantLock.lock()
            if(databaseOpenCloserBalance == 0){
                sqLiteDatabase = dbHelper.writableDatabase
            }
            ++databaseOpenCloserBalance
            return sqLiteDatabase
        } finally{
            reentrantLock.unlock()
        }
    }

    fun close(){
        try{
            reentrantLock.lock()
            --databaseOpenCloserBalance
            if(databaseOpenCloserBalance == 0){
                sqLiteDatabase.close()
            }
        } finally {
            reentrantLock.unlock()
        }
    }
}