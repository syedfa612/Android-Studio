package com.example.a3rdexperiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CourseViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<CourseItem>>()
    val courses: LiveData<List<CourseItem>> get() = _courses

    private val _selectedCourse = MutableLiveData<CourseItem?>()
    val selectedCourse: LiveData<CourseItem?> get() = _selectedCourse

    init {
        loadMockCourses()
    }

    private fun loadMockCourses() {
        _courses.value = listOf(
            CourseItem(
                id = 1,
                title = "Android Development",
                category = "Mobile",
                description = "Master the art of building native Android applications. This course covers core Kotlin concepts, Jetpack components, Fragment life cycles, layout management, background processing, and building responsive user interfaces for modern devices.",
                difficulty = "Intermediate",
                duration = "12 Weeks"
            ),
            CourseItem(
                id = 2,
                title = "Kotlin Programming",
                category = "Languages",
                description = "Learn Kotlin from the ground up. Explore object-oriented programming, modern functional paradigms, null-safety features, coroutines for asynchronous operations, and extension functions that make Kotlin one of the most loved modern programming languages.",
                difficulty = "Beginner",
                duration = "6 Weeks"
            ),
            CourseItem(
                id = 3,
                title = "Full-Stack Web Dev",
                category = "Web",
                description = "Design and deploy scalable web apps. Learn HTML5, CSS3 Grid and Flexbox, JavaScript ES6+, frontend frameworks like React, backend APIs with Node.js/Express, database systems (SQL and NoSQL), and cloud deployment workflows.",
                difficulty = "Advanced",
                duration = "16 Weeks"
            ),
            CourseItem(
                id = 4,
                title = "Data Science with Python",
                category = "Data Science",
                description = "Transform raw data into meaningful insights. Learn how to clean, analyze, and visualize complex datasets using NumPy, Pandas, Matplotlib, Seaborn, and build fundamental regression and classification models using Scikit-Learn.",
                difficulty = "Intermediate",
                duration = "10 Weeks"
            ),
            CourseItem(
                id = 5,
                title = "Machine Learning A-Z",
                category = "AI & ML",
                description = "Dive into the mechanics of Artificial Intelligence. Master algorithms like Support Vector Machines, Random Forests, K-Means clustering, and advance to deep neural network concepts using TensorFlow and Keras frameworks.",
                difficulty = "Advanced",
                duration = "14 Weeks"
            )
        )
    }

    fun selectCourse(course: CourseItem) {
        _selectedCourse.value = course
    }

    fun clearSelection() {
        _selectedCourse.value = null
    }
}
