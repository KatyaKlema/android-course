package com.example.notes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReadActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        var b: Bundle? = intent.extras
        val tv = findViewById<TextView>(R.id.textView) as TextView
        if (b != null) {
            tv.text = b.getString("key")
        }
    }
}

