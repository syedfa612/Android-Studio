package com.example.exp_5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val result = findViewById<TextView>(R.id.result)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val email = intent.getStringExtra("email")

        result.text = getString(R.string.user_details_format, name, age, email)

        btnBack.setOnClickListener {
            finish()
        }
    }
}