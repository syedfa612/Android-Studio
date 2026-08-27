# Experiment 4: Linking Activities Using Intents

An Android application built using Kotlin and XML layouts that demonstrates how to link multiple Activities and pass data between them using Intents. The application collects user inputs (Username, Age, and Email) in a login form and sends them to a second Activity to be displayed.

---

## 📱 Application Overview
The application consists of two screens:
1. **MainActivity:** A modern login/input screen designed with gradient backgrounds and card containers. Users can enter their Username, Age, and Email.
2. **SecondActivity:** A user details display screen that receives the user input via Intent Extras and presents them in a card. It also includes a "Back" button to finish the Activity and return to the main screen.

### 📸 Screenshots
| Welcome Back / Login | User Details / Second Activity |
| :---: | :---: |
| ![Welcome Back Screen](screenshots/screenshot_login.jpg) | ![User Details Screen](screenshots/screenshot_details.png) |

---

## 🛠️ Architecture & Setup
* **Language:** Kotlin
* **Minimum SDK:** 24
* **Compile SDK:** 34
* **UI Layout:** XML (ConstraintLayout with custom card views and text input layouts)

---

## 🔍 Core Concepts Demonstrated

### 1. Explicit Intent
An **Explicit Intent** is used to start a specific component (like an Activity class) within your own application. In this project, we start `SecondActivity` using:
```kotlin
val intent = Intent(this, SecondActivity::class.java)
```

### 2. Passing Data via Intent Extras
We transfer text inputs from the input fields to the next activity by calling `.putExtra(key, value)` on the intent object before starting the target activity:
```kotlin
intent.putExtra("name", name.text.toString())
intent.putExtra("age", age.text.toString())
intent.putExtra("email", email.text.toString())
startActivity(intent)
```

### 3. Retrieving Data in the Target Activity
In `SecondActivity`, we retrieve the passed values using the helper methods of the `intent` object:
```kotlin
val name = intent.getStringExtra("name")
val age = intent.getStringExtra("age")
val email = intent.getStringExtra("email")
```

### 4. Back Navigation
Tapping the "Back" button calls `finish()`, which pops the current activity off the task back stack and returns the user to `MainActivity`.
