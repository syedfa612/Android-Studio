# Experiment 7: Adaptive UI using ListView and ImageView

## Experiment Description
The objective of this experiment is to create an adaptive Android application user interface (UI) utilizing `ListView` and `ImageView`. The UI should adapt its layout dynamically based on the device's orientation (portrait or landscape).

## Concept & Technology
**Adaptive UI**
An adaptive user interface in Android is one that responds to different screen configurations, such as changes in orientation (portrait to landscape), different screen sizes, or fold state. Android provides a robust resource qualification system, enabling developers to define alternative layouts (e.g., `res/layout` for default/portrait and `res/layout-land` for landscape). The Android OS automatically inflates the correct layout at runtime based on the device's current configuration.

**ListView & ImageView**
- **ListView**: A view group that displays a list of scrollable items. In this experiment, it is populated using an `ArrayAdapter` containing a list of fruit names.
- **ImageView**: A view used to display arbitrary images, such as icons or photographs. In this experiment, it dynamically displays vector graphics based on the item selected in the `ListView`.

**Scenario**
We have built a simple "Fruit Viewer" app. The application features a list of fruits ("Apple", "Banana", "Cherry") and an image view representing the selected fruit.
- In **Portrait mode**, the UI is arranged vertically: the student information sits at the top, followed by the fruit image, and the list of fruits at the bottom.
- In **Landscape mode**, horizontal space is plentiful, but vertical space is limited. Thus, the UI adapts to place the list of fruits and the fruit image side-by-side to optimize screen real estate.
- Tapping on a list item updates the image dynamically using an `OnItemClickListener`.

## Project File & Folder Structure
```
app/
├── src/
│   └── main/
│       ├── java/com/example/experiment7createanadaptiveuiusinglistviewandimageviewgithubsubmissionlink/
│       │   └── MainActivity.kt       # Contains the main logic, ListView initialization and Click Listener
│       └── res/
│           ├── drawable/             # Vector Drawables for images
│           │   ├── ic_apple.xml
│           │   ├── ic_banana.xml
│           │   └── ic_cherry.xml
│           ├── layout/               # Default / Portrait Layout
│           │   └── activity_main.xml
│           └── layout-land/          # Landscape Layout
│               └── activity_main.xml
├── build.gradle.kts                  # Module-level build script
└── AndroidManifest.xml
```

## Test Cases & Screenshots

### Test Case 1: Initial State (Portrait) & USN Validation
**Description:** Verify that the app launches in portrait mode with the default image displayed. The student's name and USN must be visible at the top.
- **Action:** Launch the app in portrait orientation.
- **Expected Result:** The Apple image is shown by default. The `ListView` displays the fruits. The USN (25MCAR0138) and Name (Syed Faizan Pasha) are clearly visible.

![Test Case 1](testcase1_portrait_initial.png)

### Test Case 2: Image Selection Interaction
**Description:** Verify that interacting with the `ListView` correctly updates the `ImageView`.
- **Action:** Tap on the second item ("Banana") in the `ListView`.
- **Expected Result:** The `ImageView` updates its source to display the Banana icon.

![Test Case 2](testcase2_portrait_selected.png)

### Test Case 3: Adaptive Layout (Landscape)
**Description:** Verify that the UI adapts to a landscape orientation appropriately.
- **Action:** Rotate the device/emulator to Landscape mode.
- **Expected Result:** The layout shifts from a vertical stack to a side-by-side arrangement. The `ListView` and `ImageView` are positioned horizontally adjacent to each other.

![Test Case 3](testcase3_landscape.png)
