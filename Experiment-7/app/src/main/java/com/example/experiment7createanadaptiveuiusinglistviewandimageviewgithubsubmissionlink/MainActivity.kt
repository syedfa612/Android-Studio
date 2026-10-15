package com.example.experiment7createanadaptiveuiusinglistviewandimageviewgithubsubmissionlink

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val imageView: ImageView = findViewById(R.id.imageView)

        val fruits = arrayOf("Apple", "Banana", "Cherry")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruits)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> imageView.setImageResource(R.drawable.ic_apple)
                1 -> imageView.setImageResource(R.drawable.ic_banana)
                2 -> imageView.setImageResource(R.drawable.ic_cherry)
            }
        }
    }
}