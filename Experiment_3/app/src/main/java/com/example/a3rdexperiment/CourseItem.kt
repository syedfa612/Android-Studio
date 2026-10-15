package com.example.a3rdexperiment

import java.io.Serializable

data class CourseItem(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val difficulty: String,
    val duration: String
) : Serializable
