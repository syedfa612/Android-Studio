# Implementation Plan - Fix Build Errors and Debugging Guide

The project currently has two build-blocking issues:
1.  **SDK Version Mismatch**: Dependencies require `compileSdk 37`.
2.  **Layout Resource Linking Errors**: Incorrect attribute names `layout_bottom_toBottomOf` used instead of `layout_constraintBottom_toBottomOf`.

## Proposed Changes

### [Component Name] Build Configuration

#### [MODIFY] [build.gradle.kts](file:///D:/3rdExperiment/app/build.gradle.kts)
- Update `compileSdk` to `37`.
- Update `targetSdk` to `37`.

### [Component Name] Layout Resources

#### [MODIFY] [activity_main.xml](file:///D:/3rdExperiment/app/src/main/res/layout/activity_main.xml)
- Correct `app:layout_bottom_toBottomOf` to `app:layout_constraintBottom_toBottomOf`.

#### [MODIFY] [activity_main.xml (landscape)](file:///D:/3rdExperiment/app/src/main/res/layout-land/activity_main.xml)
- Correct `app:layout_bottom_toBottomOf` to `app:layout_constraintBottom_toBottomOf`.

## Debugging Workflow

I will provide a walkthrough that guides you through:
1.  **Setting a Normal Breakpoint**: In `DetailFragment.kt` to inspect the Fragment lifecycle and variables.
2.  **Setting a Conditional Breakpoint**: In `ListFragment.kt` for specific item selection ("Android Development").
3.  **Analysis**: Explaining the difference between normal and conditional breakpoints.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to ensure configuration is valid.
- Run `gradle_build` (or `app:assembleDebug`) to verify resource linking and SDK compatibility.

### Manual Verification
- Deploy to emulator/device.
- Verify master-detail behavior in both portrait and landscape.
