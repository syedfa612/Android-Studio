package com.qrvault.app.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.qrvault.app.databinding.ViewHeaderBinding

fun Context.toast(@StringRes resId: Int) =
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.setupHeader(header: ViewHeaderBinding, title: String, showBack: Boolean = true) {
    header.tvTitle.text = title
    if (showBack) {
        header.btnBack.setOnClickListener { finish() }
    } else {
        header.btnBack.visibility = View.GONE
    }
}
