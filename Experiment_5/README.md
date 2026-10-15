# MAD Experiment 5: Develop an Application for Displaying Notifications in Android

## 📱 Project Overview

This project demonstrates how to create and display **notifications in an Android application using Kotlin**.

The application is an extension of **Experiment 4 – Linking Activities Using Intents**. When the user enters their **Name, Age, and Email** and clicks the **LOGIN** button, a notification is generated indicating that the user has successfully logged in.

After login, the application also opens the second Activity and displays the user's details. A **Back** button is provided to return to the Login Page.

---

## 🎯 Aim

To develop an Android application using Kotlin that displays a **successful login notification** after the user logs in and demonstrates the use of Android notification channels and notification permissions.

---

## 📚 Theory

A **notification** is a message displayed by Android outside the application's main user interface. It can inform users about events, updates, or actions performed by an application.

In this experiment, a notification is generated when the user clicks the **LOGIN** button. The notification contains a title such as **"Login Successful"** and a message such as **"You logged in successfully."**

For Android 8.0 and above, notifications are created using a **Notification Channel**. Android 13 and above also requires the `POST_NOTIFICATIONS` permission before an application can display notifications.

---

## 🔄 Application Flow

```text
              Login Page
                  │
                  ↓
       Enter Name, Age & Email
                  │
                  ↓
             Click LOGIN
                  │
          ┌───────┴────────┐
          ↓                ↓
   Login Notification   Second Activity
          │                │
          ↓                ↓
 Login Successful     User Details
          │                │
          └───────┬────────┘
                  ↓
               BACK
                  ↓
             Login Page
```

---

## ✨ Features

* 🎨 Modern and aesthetic Login Page
* 👤 User input for Name
* 🎂 User input for Age
* 📧 User input for Email
* 🔔 Login success notification
* ⏰ Notification timestamp provided by Android
* 📱 Application name displayed in the notification
* 🔗 Explicit Intent for Activity navigation
* 📤 Data passing using `putExtra()`
* 📥 Data receiving using `getStringExtra()`
* ↩️ Back button on the second Activity
* 🔐 Notification permission support for Android 13+
* 📢 Notification channel support for Android 8+

---

## 🛠️ Technologies Used

| Technology          | Purpose                 |
| ------------------- | ----------------------- |
| Kotlin              | Programming Language    |
| Android Studio      | Development Environment |
| XML                 | User Interface Design   |
| Android SDK         | Application Development |
| Explicit Intent     | Linking Activities      |
| NotificationCompat  | Creating Notifications  |
| NotificationChannel | Managing Notifications  |
| `putExtra()`        | Passing User Data       |
| `getStringExtra()`  | Receiving User Data     |

---

## 📂 Project Structure

```text
MAD-Experiment-5-Android-Notifications/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── package/
│           │       ├── MainActivity.kt
│           │       └── SecondActivity.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   │   ├── bg_gradient.xml
│           │   │   ├── input_background.xml
│           │   │   └── button_background.xml
│           │   │
│           │   └── layout/
│           │       ├── activity_main.xml
│           │       └── activity_second.xml
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── .gitignore
└── README.md
```

---

# 🖥️ User Interface

## 1. Login Page

The first Activity contains an aesthetic login interface with:

* User icon
* Welcome message
* Name field
* Age field
* Email field
* Login button
* Purple gradient background
* Rounded input fields

### 📸 Screenshot

Place your screenshot in:

```text
screenshots/login-page.png
```

Then display it using:

```markdown
![Login Page](screenshots/login-page.png)
```

---

## 2. Login Notification

After entering the details and clicking **LOGIN**, Android displays a notification.

Example:

```text
Application Name                         10:25 PM

Login Successful

You logged in successfully.
```

## 3. User Details Page

After successful login, the second Activity displays the information entered on the Login Page.

Example:

```text
User Details

Name: Vishwajeet Singh

Age: 24

Email: vickeysingh596.25@gmail.com

[ BACK ]
```

### 📸 Screenshot
<img width="720" height="1600" alt="WhatsApp Image 2026-08-24 at 10 42 59 PM" src="https://github.com/user-attachments/assets/9d2272f2-3daf-477b-8cdd-efce6d5859e5" />
<img width="720" height="1600" alt="WhatsApp Image 2026-08-24 at 10 42 59 PM (2)" src="https://github.com/user-attachments/assets/ab0592c9-225c-4352-bac1-488358b1ec49" />
<img width="720" height="1600" alt="WhatsApp Image 2026-08-24 at 10 42 59 PM (1)" src="https://github.com/user-attachments/assets/48bd98cf-ca04-41be-bcc2-a3775326b0df" />

## 4. Back Button

The second Activity contains a **Back** button that returns the user to the Login Page.

### 📸 Screenshot

Place your screenshot in:

```text
screenshots/back-button.png
```

Then add:

```markdown
![Back Button](screenshots/back-button.png)
```

---

# 💻 Implementation

## 1. Creating the Notification Channel

A notification channel is created for Android 8.0 and above.

```kotlin
private fun createNotificationChannel() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val channel = NotificationChannel(
            channelId,
            "Login Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager =
            getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(channel)
    }
}
```

---

## 2. Creating the Login Notification

The notification is generated after the user clicks the Login button.

```kotlin
val notification = NotificationCompat.Builder(
    this,
    channelId
)
    .setSmallIcon(R.mipmap.ic_launcher)
    .setContentTitle("Login Successful")
    .setContentText("You logged in successfully.")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setAutoCancel(true)
    .setShowWhen(true)
    .build()

NotificationManagerCompat
    .from(this)
    .notify(1001, notification)
```

---

## 3. Passing User Data

The user details are passed to the second Activity using an Explicit Intent.

```kotlin
val intent = Intent(this, SecondActivity::class.java)

intent.putExtra("name", name.text.toString())
intent.putExtra("age", age.text.toString())
intent.putExtra("email", email.text.toString())

startActivity(intent)
```

---

## 4. Receiving User Data

The second Activity receives the data using `getStringExtra()`.

```kotlin
val name = intent.getStringExtra("name")
val age = intent.getStringExtra("age")
val email = intent.getStringExtra("email")
```

The details are then displayed:

```kotlin
result.text = "Name: $name\n\nAge: $age\n\nEmail: $email"
```

---

# 🔔 Notification Permission

For Android 13 and above, the application requests notification permission using:

```text
POST_NOTIFICATIONS
```

The permission is declared in `AndroidManifest.xml`:

```xml
<uses-permission
    android:name="android.permission.POST_NOTIFICATIONS" />
```

If permission has not been granted, Android displays a permission dialog.

The user must select **Allow** for the application to display the login notification.

---

# ▶️ How to Run the Project

### Step 1

Clone the repository:

```bash
git clone https://github.com/Vishwajeetsingh22/MAD-Experiment-5-Android-Notifications.git
```

### Step 2

Open the project in **Android Studio**.

### Step 3

Wait for Gradle synchronization to complete.

### Step 4

Connect an Android device or start an Android Emulator.

### Step 5

Click:

```text
Run ▶
```

### Step 6

Enter sample details:

```text
Name: Vishwajeet singh
Age: 25
Email: vickeysingh596.25@gmail.com
```

### Step 7

Click **LOGIN**.

### Step 8

Allow notification permission if Android asks for it.

### Step 9

Check the notification panel for:

```text
Login Successful
You logged in successfully.
```

### Step 10

The second Activity displays the entered user details.

---

# 🧪 Sample Input

```text
Name: Vishwajeet singh 
Age: 25 
Email: vickeysingh596.25@gmail.com
```

---

# 📤 Expected Output

### Notification

```text
Login Successful
You logged in successfully.
```

### Second Activity

```text
User Details

Name: Vishwajeet singh 
Age: 25 
Email: vickeysingh596.25@gmail.com

[ BACK ]
```

---

# 📖 Concepts Demonstrated

1. Android Notifications
2. Notification Channel
3. Notification Permission
4. `NotificationCompat.Builder`
5. `NotificationManagerCompat`
6. Explicit Intent
7. Activity Navigation
8. `putExtra()`
9. `getStringExtra()`
10. `startActivity()`
11. `finish()`
12. XML Layout Design
13. Button Click Events

---

# 🎓 Learning Outcome

After completing this experiment, we understand how to:

* Create notifications in Android.
* Create and use a notification channel.
* Handle notification permission on Android 13+.
* Display a login success notification.
* Link two Activities using Explicit Intent.
* Pass data between Activities.
* Receive and display transferred data.
* Navigate back to the previous Activity.

---

# ✅ Result

The Android application was successfully developed using **Kotlin** to display a login success notification. After entering the user details and clicking the Login button, a notification is generated with the message **"Login Successful – You logged in successfully."** The user details are also successfully transferred to the second Activity and displayed.

---

# 👨‍💻 Author

**Name:** Vishwajeet Singh
**USN:** 25MCAR0219
**Course:** Master of Computer Applications (MCA)
**College:** Jain (Deemed-to-be University)
**Campus:** JGI Knowledge Campus, Jayanagar, Bangalore

---

# 🔗 GitHub Repository

**MAD Experiment 5 – Android Notifications**

```text
https://github.com/Vishwajeetsingh22/MAD-Experiment-5-Android-Notifications
```

---

## 📌 Experiment Details

**Experiment No.:** 5
**Subject:** Mobile Application Development (MAD)
**Topic:** Developing an Application for Displaying Notifications in Android
**Language:** Kotlin
**IDE:** Android Studio
**Platform:** Android
