package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardViewText : CardView = findViewById<CardView>(R.id.cardView)
        cardViewText.setOnClickListener{
            val intent = Intent(this, ReadActivity :: class.java)
            startActivity(intent)
        }
    }
}