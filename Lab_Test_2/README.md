# Lab Test 2: QRVault - Contact QR Code Generator & Storage App

## Overview
**QRVault** is an Android application designed to seamlessly convert phone contacts into scannable **vCard 3.0 QR codes**. It leverages the Android **Contacts ContentProvider** for contact fetching, **ZXing** for high-quality QR generation, **SQLite** for maintaining local history, and **MediaStore / FileProvider** for saving and sharing generated QR codes.

---

## Features
- **Splash Screen**: Animated branded introduction screen.
- **Fetch Contacts**: Accesses on-device contacts securely using runtime permissions and `ContactsContract`.
- **QR Code Generation**: Encodes selected contact details (Name, Phone Number, Email) into standard vCard 3.0 QR code format.
- **Local History (SQLite)**: Automatically logs generated QR records into a local SQLite database (`QRVault.db`) with timestamps.
- **Save & Share**: Save QR codes directly to the device image gallery via MediaStore or share via external apps.
- **Modern UI/UX**: Clean layout designed with Material 3 components, rounded cards, and responsive navigation.

---

## Screenshots & App Flow

### 1. Splash & Home Dashboard
| Splash Screen | Home Dashboard |
| :---: | :---: |
| ![Splash Screen](./screenshots/splash_screen.png) | ![Home Dashboard](./screenshots/main_screen.png) |

### 2. Contact Selection & Details
| Contacts List | Contact Details |
| :---: | :---: |
| ![Contacts List](./screenshots/contacts_screen.png) | ![Contact Details](./screenshots/contact_details_screen.png) |

### 3. Generated QR Code & Local History
| Generated QR Code | QR History |
| :---: | :---: |
| ![Generated QR Code](./screenshots/qr_code_screen.png) | ![QR History](./screenshots/qr_history_screen.png) |

### 4. About Application
| About Screen |
| :---: |
| ![About Screen](./screenshots/about_screen.png) |

---

## Tech Stack
- **Language**: Kotlin
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **UI Framework**: Android XML Layouts, Material Design 3, View Binding
- **QR Code Engine**: ZXing (`com.google.zxing:core:3.5.3`)
- **Database**: SQLite (`SQLiteOpenHelper`)
- **Content Providers**: `ContactsContract`, `FileProvider`, `MediaStore`

---

## Project Structure
```text
Lab_Test_2/
├── app/
│   ├── src/main/java/com/qrvault/app/
│   │   ├── adapter/         # Contact & History RecyclerView Adapters
│   │   ├── data/            # SQLite OpenHelper & Repository
│   │   ├── model/           # Contact & QrRecord Data Models
│   │   ├── ui/              # Activities (Splash, Main, Contacts, QR, History, About)
│   │   └── util/            # QR Generator, ImageStorage, PermissionHelper
│   └── src/main/res/        # Layouts, Drawables, Values, Nav XMLs
├── screenshots/             # Application Screenshot Images
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## How to Run
1. Open the project folder in **Android Studio**.
2. Sync Gradle dependencies.
3. Select an Android Emulator or connected device (API 24+).
4. Run `:app` configuration.
