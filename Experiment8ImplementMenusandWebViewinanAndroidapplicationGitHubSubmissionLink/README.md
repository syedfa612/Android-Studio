# Experiment 8: Implement Menus and WebView in an Android Application

## Description
This project demonstrates the implementation of Menus and a WebView in an Android application. The experiment showcases how to navigate between different sections of the app using a menu (such as an Options Menu or a Top App Bar with actions) and how to display web content seamlessly within the app using a `WebView` component.

## Concept & Technology
- **Menus**: Used to provide user actions and navigation options within the app. Menus can be integrated into the Top App Bar for easy access to different app features.
- **WebView**: A view that displays web pages inside your application. It allows you to embed a web browser within your app to show online content or specify HTML string data to display directly.
- **Jetpack Compose / XML Layouts**: The UI toolkit used for building the native Android UI, allowing us to declare the UI elements, integrate the WebView, and manage navigation states effectively.
- **Internet Permission**: Utilizing `<uses-permission android:name="android.permission.INTERNET" />` in the AndroidManifest.xml to allow the WebView to fetch online resources.

## Scenario
**"Student Portal Web Viewer"**: An application that provides students with quick access to their college portal, course materials, and a personal profile. The app features a top menu with options to navigate between "Home", "Profile", and "Portal". 
- Selecting the **"Portal"** menu option opens a WebView loading the college website.
- Selecting the **"Profile"** menu option displays the student's USN and Name.

## Folder and File Structure
```text
Experiment8ImplementMenusandWebView/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml       # Declares app permissions (e.g., INTERNET) and components
│   │   │   ├── java/com/example/.../     # Contains the Kotlin source code (MainActivity)
│   │   │   │   ├── MainActivity.kt       # Main entry point, Menu setup, and WebView integration
│   │   │   │   └── ui/theme/             # Theme configurations (Color, Theme, Type)
│   │   │   └── res/                      # Resources (drawables, values, mipmap icons)
│   │   ├── test/                         # Local unit tests
│   │   └── androidTest/                 # Instrumented UI tests
│   └── build.gradle.kts                  # App-level build configurations and dependencies
│
├── build.gradle.kts                      # Project-level build configurations
├── settings.gradle.kts                   # Defines sub-projects (app)
└── README.md                             # Project documentation (this file)
```

## Output Screenshot
*(Please upload your final app screenshot here)*
![App Output Placeholder](output_screenshot.png)

---

## Test Cases

### Test Case 1: Display USN and Name
- **Objective**: Verify that the student's USN and Name are correctly displayed in the Profile section.
- **Steps**:
  1. Launch the app.
  2. Open the top options menu.
  3. Select the "Profile" menu item.
- **Expected Result**: The screen updates to show the correct USN and Name.
- **Screenshot**:
  *(Replace with actual screenshot showing USN and Name)*
  ![Test Case 1](test_case_1_profile.png)

### Test Case 2: WebView Loading
- **Objective**: Ensure that the WebView successfully loads a specified URL when navigated to the Portal.
- **Steps**:
  1. Launch the app.
  2. Click on the "Portal" menu item.
- **Expected Result**: The WebView component appears and successfully loads the target website (e.g., google.com or the college portal).
- **Screenshot**:
  *(Replace with actual screenshot of the WebView)*
  ![Test Case 2](test_case_2_webview.png)

### Test Case 3: Menu Navigation and State Switching
- **Objective**: Validate that switching between different menu options works seamlessly without crashing and correctly updates the UI.
- **Steps**:
  1. Launch the app.
  2. Tap on "Portal" (wait for it to load).
  3. Tap on "Home".
  4. Tap on "Profile".
- **Expected Result**: The UI updates instantly based on the selected menu item without overlapping views or application crashes.
- **Screenshot**:
  *(Replace with actual screenshot of the Home/Navigation menu state)*
  ![Test Case 3](test_case_3_navigation.png)
