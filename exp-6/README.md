# Experiment 6: Develop an Android Application Using Basic Views

## Overview
This repository contains the source code and documentation for **Experiment 6**, focusing on developing a native Android application using **basic Android Views** (XML layouts and UI widgets) in Kotlin.

---

## Concept & Technology
In Android development, UI design can be implemented using traditional XML-based layouts (**Android Views**) or modern declarative UI frameworks (**Jetpack Compose**). This experiment demonstrates foundational Android View development:
- **`ViewGroup` / Containers**: `ScrollView`, `LinearLayout` for structuring layout hierarchies.
- **Interactive UI Widgets (`View`)**:
  - `EditText`: For user text input (Name, USN).
  - `TextView`: For displaying static labels and dynamic output summaries.
  - `Button`: For triggering event listeners upon user interaction.
  - `RadioGroup` & `RadioButton`: For mutual-exclusion choices (Gender).
  - `Spinner`: For dropdown selection lists (Course selection).
  - `CheckBox`: For boolean toggle options (Notifications).
- **View Binding & Interaction**: Using `findViewById` and `ArrayAdapter` to dynamically bind data and handle event callbacks in `MainActivity`.

---

## Scenario: Student Course Registration Portal
To demonstrate basic Android Views, this application implements a **Student Course Registration Portal**. Students can enter their Name, USN, select their Gender, choose a course from a dropdown list, opt-in for notifications, and submit their registration. The app validates inputs and outputs a formatted summary card.

---

## Project Folder and File Structure
```text
Experiment6DevelopanAndroidapplicationusingbasicViewsGitHubSubmissionLink/
├── .github/                         # GitHub configuration / workflows
├── app/
│   ├── build.gradle.kts             # App-level build configuration (Compile/Target SDK 37)
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml  # Application manifest configuration
│       │   ├── java/com/example/experiment6.../
│       │   │   └── MainActivity.kt  # Main Activity handling view binding & event logic
│       │   └── res/
│       │       ├── layout/
│       │       │   └── activity_main.xml # XML layout defining basic Views
│       │       ├── values/
│       │       │   ├── colors.xml       # Color palette definitions
│       │       │   ├── strings.xml      # String resources
│       │       │   └── themes.xml       # App theme definitions
│       │       └── mipmap-*/            # Launcher icons
│       └── test/ & androidTest/     # Unit and instrumentation tests
├── build.gradle.kts                 # Root build configuration
├── settings.gradle.kts              # Gradle settings
└── README.md                        # Comprehensive experiment documentation
```

---

## Output & Test Cases

### 1. Successful Registration Test Case (Featuring USN and Name)
* **Test Input**:
  - Name: `Rahul Sharma`
  - USN: `1RV21CS045`
  - Gender: `Male`
  - Course: `Android Mobile App Development`
  - Notifications: Checked (`Subscribed`)
* **Expected Output**:
  - Displays success message with timestamp, Student Name (`Rahul Sharma`), USN (`1RV21CS045`), Gender, Course, and Notification status.
* **Screenshot Reference**:
  > ![Output Screenshot 1](app/src/main/res/drawable/ic_launcher_background.xml) *(Placeholder for device screenshot showing successful registration with USN and Name)*

### 2. Validation Error Test Case (Missing USN / Name)
* **Test Input**:
  - Name: (Left blank)
  - USN: (Left blank)
* **Expected Output**:
  - Highlights missing fields with error hints (`Please enter student name`, `Please enter student USN`) and shows a Toast message (`Name is required`).

### 3. Alternative Course & Gender Selection Test Case
* **Test Input**:
  - Name: `Ananya Rao`
  - USN: `1RV21IS012`
  - Gender: `Female`
  - Course: `Database Management Systems`
  - Notifications: Unchecked
* **Expected Output**:
  - Successfully registers and displays summary showing `Ananya Rao`, `1RV21IS012`, `Female`, `Database Management Systems`, and `Notifications: Not Subscribed`.

---

## GitHub Submission Link
[GitHub Repository Submission Link](https://github.com/example/Experiment6-Android-Basic-Views)
