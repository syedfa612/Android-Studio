# Walkthrough - Master-Detail App & Debugging Guide

I have fixed the build errors and verified the Master-Detail application. Below is the guide for the debugging tasks you requested.

## Changes Made

### 1. Build & Layout Fixes
- **SDK Update**: Updated `compileSdk` and `targetSdk` to `37` in `app/build.gradle.kts` to satisfy `androidx.core` dependency requirements.
- **Resource Linking**: Fixed incorrect attribute names in `activity_main.xml` (both portrait and landscape) from `app:layout_bottom_toBottomOf` to the correct `app:layout_constraintBottom_toBottomOf`.

### 2. Verified Master-Detail Logic
- The app uses a shared `CourseViewModel` to communicate between `ListFragment` and `DetailFragment`.
- The layout adapts automatically to screen orientation:
    - **Portrait**: Single pane using `fragment_container`.
    - **Landscape**: Dual pane using `list_container` and `detail_container`.

---

## Debugging Task Guide

### Task 1: Normal Breakpoint in DetailFragment
**Goal**: Observe Fragment lifecycle, local variables, and call stack when the Fragment is created.

1.  Open [DetailFragment.kt](file:///D:/3rdExperiment/app/src/main/java/com/example/a3rdexperiment/DetailFragment.kt).
2.  Place a **Breakpoint** on line 53: `emptyState.visibility = View.GONE`.
3.  Run the app in **Debug Mode** (Shift+F9).
4.  Select any item from the list.
5.  **Observation**:
    - **Lifecycle**: Check the **Logcat**; you will see `onCreate`, `onCreateView`, and `onViewCreated` logs. The debugger pauses during the `onViewCreated` phase where the observer is triggered.
    - **Local Variables**: In the **Variables** tab, you can inspect the `course` object (title, category, etc.) and the UI components like `textTitle` and `textDescription`.
    - **Call Stack**: Look at the **Frames** window. You'll see the stack starting from Android's main looper, through LiveData's observer notification, to your fragment code.

### Task 2: Conditional Breakpoint in Item Selection
**Goal**: Pause execution only when a specific item ("Android Development") is selected.

1.  Open [ListFragment.kt](file:///D:/3rdExperiment/app/src/main/java/com/example/a3rdexperiment/ListFragment.kt).
2.  Place a **Breakpoint** on line 36: `viewModel.selectCourse(course)`.
3.  **Right-click** the breakpoint dot (red circle).
4.  In the **Condition** field, enter: `course.title == "Android Development"`.
5.  Click **Done**.
6.  Run the app in **Debug Mode**.
7.  Select "Kotlin Programming" -> The app **does not pause**.
8.  Select "Android Development" -> The app **pauses**.

---

## Breakpoint Comparison

| Feature | Normal Breakpoint | Conditional Breakpoint |
| :--- | :--- | :--- |
| **Trigger** | Every time the line of code is reached. | Only when the line is reached **AND** the boolean condition is true. |
| **Best Use Case** | General inspection of logic flow or variable states. | Debugging specific data edge cases (e.g., a specific ID) without stopping on every iteration or click. |
| **Performance** | Minimal impact on performance. | Slightly slower as the debugger evaluates the condition each time the line is hit. |

> [!TIP]
> Use the **"Evaluate Expression"** tool (Alt+F8) while paused to test custom logic or modify variable values on the fly!
