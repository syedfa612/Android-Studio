# Experiment 2: Activity Lifecycle Implementation

## Concept
The **Android Activity Lifecycle** is a set of states an activity can be in during its entire lifetime, from the time it's created until it's destroyed. The Android system uses callback methods to notify the activity when it's transitioning between these states. Understanding this is crucial for managing resources, saving UI state, and handling user interruptions.

## Scenario
This application demonstrates the lifecycle by:
1. Displaying the student's **Name** and **USN**.
2. Showing the current lifecycle state in a **TextView**.
3. Displaying **Toast** messages and **Logcat** entries for every state transition.

### Lifecycle Methods Demonstrated:
- `onCreate()`: Called when the activity is first created.
- `onStart()`: Called when the activity becomes visible to the user.
- `onResume()`: Called when the activity starts interacting with the user.
- `onPause()`: Called when the activity is no longer in the foreground.
- `onStop()`: Called when the activity is no longer visible.
- `onRestart()`: Called when the activity is stopped and then started again.
- `onDestroy()`: Called before the activity is destroyed.

## Project Structure
```
Experiment2/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/experiment2/
│   │   │   │   └── MainActivity.kt       # Logic for lifecycle overrides
│   │   │   └── res/
│   │   │       └── layout/
│   │   │           └── activity_main.xml # UI with state display
│   └── build.gradle.kts
└── README.md                             # This documentation
```

## How to Test
1. **Launch the App**: Observe the state change to `onResume`.
2. **Press Home**: Observe the state change to `onStop`.
3. **Switch Back**: Observe the state change to `onResume` (via `onRestart`).
4. **Press Back**: Observe `onDestroy` in Logcat.

---
**Developer:** Syed Faizan Pasha
**USN:** 25mcar0138
