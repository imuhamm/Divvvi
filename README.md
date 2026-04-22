# Divvvi

> **Scan it. Adjust it. Done.**
> The fastest way to split a receipt with your roommates.

Divvvi is a native Android app that scans a receipt, extracts items via on-device OCR, lets you tweak assignments per person, and saves the split. Built for small households (max 5 members per group).

Status: **MVP in development** — see [plan.md](plan.md) for milestones.

---

## Stack

- **Kotlin** + **Jetpack Compose** (min SDK 26, target 35)
- **Firebase** — Anonymous Auth, Firestore (free Spark plan; Storage intentionally not used)
- **ML Kit Text Recognition** — on-device OCR, free and unlimited
- **CameraX** for capture
- **Coil** for images
- **Navigation Compose** for routing

See [plan.md](plan.md) for the full data model, milestones, and technical rationale.

---

## Getting Started

### Prerequisites
- Android Studio Ladybug (2024.2) or newer
- JDK 17
- An Android device or emulator running API 26+

### Firebase setup (required before first run)
1. Create a project at [console.firebase.google.com](https://console.firebase.google.com) on the free Spark plan.
2. Add an Android app with package name `divvvi.app`.
3. Download `google-services.json` and place it in `app/` — this file is gitignored.
4. In the Firebase console, enable:
   - **Authentication → Sign-in method → Anonymous**
   - **Firestore Database** (start in test mode for local dev)

> Storage is intentionally not used — it now requires the paid Blaze plan to enable, and the MVP performs OCR on-device without persisting receipt images.

### Build & run
```bash
./gradlew :app:installDebug
```
Or open the project in Android Studio and hit **Run**.

---

## Project Structure

```
app/src/main/java/com/divvvi/app/
├── MainActivity.kt
├── DivvviApp.kt              # Application class
├── ui/
│   ├── DivvviApp.kt          # NavHost + routes
│   ├── theme/                # Compose theme
│   └── screens/              # Splash, Group, Scan, Review, Assign, Confirm, History
└── data/
    └── model/                # Firestore data classes
```

---

## Roadmap

**MVP (Phase 1):** group management, OCR scan, review/edit, item assignment, confirm, history.
**Phase 2:** per-user totals, "who owes whom," payment tracking, balance dashboard.

Full breakdown in [plan.md](plan.md).

---

## License

Private / TBD.
