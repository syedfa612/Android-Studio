package com.example.experiment6developanandroidapplicationusingbasicviewsgithubsubmissionlink

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    private lateinit var etStudentName: EditText
    private lateinit var etStudentUsn: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spinnerCourse: Spinner
    private lateinit var cbNotifications: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var tvOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        etStudentName = findViewById(R.id.etStudentName)
        etStudentUsn = findViewById(R.id.etStudentUsn)
        rgGender = findViewById(R.id.rgGender)
        spinnerCourse = findViewById(R.id.spinnerCourse)
        cbNotifications = findViewById(R.id.cbNotifications)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvOutput = findViewById(R.id.tvOutput)

        // Setup Spinner Adapter
        val courses = arrayOf(
            "Android Mobile App Development",
            "Advanced Data Structures & Algorithms",
            "Operating Systems & Kernel Design",
            "Database Management Systems",
            "Computer Networks & Security"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = adapter

        // Setup Submit Button Click Listener
        btnSubmit.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val usn = etStudentUsn.text.toString().trim()

            if (name.isEmpty()) {
                etStudentName.error = "Please enter student name"
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (usn.isEmpty()) {
                etStudentUsn.error = "Please enter student USN"
                Toast.makeText(this, "USN is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not specified"
            }

            val course = spinnerCourse.selectedItem.toString()
            val notifications = if (cbNotifications.isChecked) "Subscribed" else "Not Subscribed"

            // Build output summary
            val summary = """
                ✅ Registration Successful!
                
                👤 Name: $name
                🆔 USN: $usn
                🚻 Gender: $gender
                📚 Course: $course
                📧 Notifications: $notifications
                
                Timestamp: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())}
            """.trimIndent()

            tvOutput.text = summary
            Toast.makeText(this, "Registration submitted successfully for $usn", Toast.LENGTH_SHORT).show()
        }
    }
}
