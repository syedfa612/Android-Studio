package com.example.exp_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val email = findViewById<EditText>(R.id.email)
        val login = findViewById<Button>(R.id.login)

        login.setOnClickListener {

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("name", name.text.toString())
            intent.putExtra("age", age.text.toString())
            intent.putExtra("email", email.text.toString())

            startActivity(intent)
        }
    }
}