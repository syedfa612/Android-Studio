package com.qrvault.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Long,
    val name: String,
    val phones: List<String>,
    val emails: List<String>
) : Parcelable {

    val initial: String
        get() {
            val c = name.trim().firstOrNull()
            return if (c != null && c.isLetterOrDigit()) c.uppercaseChar().toString() else "#"
        }
}
