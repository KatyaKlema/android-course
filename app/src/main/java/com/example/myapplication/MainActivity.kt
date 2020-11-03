package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonEmail : Button = findViewById<Button>(R.id.button5)
        buttonEmail.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "email@email.com", null
                )
            )
            intent.putExtra(Intent.EXTRA_TEXT, "Отправлено из ");
            startActivity(intent);
        }
        val buttonProfile : Button = findViewById<Button>(R.id.button6)
        buttonProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity :: class.java)
            startActivity(intent);
        }
    }
}
