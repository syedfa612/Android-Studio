package com.qrvault.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qrvault.app.R
import com.qrvault.app.databinding.ActivityAboutBinding
import com.qrvault.app.util.setupHeader

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeader(binding.header, getString(R.string.about_title))
    }
}
