package com.example.experiment2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvLifecycleState: TextView
    private val TAG = "ActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        tvLifecycleState = findViewById(R.id.tvLifecycleState)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        updateState("onCreate")
    }

    override fun onStart() {
        super.onStart()
        updateState("onStart")
    }

    override fun onResume() {
        super.onResume()
        updateState("onResume")
    }

    override fun onPause() {
        super.onPause()
        updateState("onPause")
    }

    override fun onStop() {
        super.onStop()
        updateState("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        updateState("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    private fun updateState(state: String) {
        Log.d(TAG, "$state called")
        tvLifecycleState.text = "State: $state"
        Toast.makeText(this, "$state called", Toast.LENGTH_SHORT).show()
    }
}
