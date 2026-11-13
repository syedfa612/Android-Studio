package com.qrvault.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Parcelize
data class QrRecord(
    val id: Long,
    val contactName: String,
    val phone: String,
    val email: String,
    val vCard: String,
    val createdAt: Long
) : Parcelable {

    val formattedDate: String
        get() = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(createdAt))
}
