package com.example.medalert_medicinereminder

import java.io.Serializable

data class Medicine(
    val name: String,
    val dosage: String,
    val type: String,
    val frequency: String,
    val hasReminder: Boolean
) : Serializable
