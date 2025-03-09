# Compose Multiplatform Project: Smart-Dash

This document serves as a guide for developers contributing to the Smart-Dash project, a Compose Multiplatform
application. It covers the project structure, key components, and best practices for development.

## Project Structure Overview

The project is organized to support Compose Multiplatform development, allowing code sharing across Android, Desktop,
and Web platforms. The main structure is as follows:

```
Smart-Dash/
├── composeApp/
│   ├── src/
│   │   ├── androidMain/
│   │   │   ├── kotlin/
│   │   │   │   └── org.smarthome/
│   │   │   │       └── MainActivity.kt
│   │   │   └── res/
│   │   │       └── AndroidManifest.xml
│   │   ├── commonMain/
│   │   │   ├── composeResources/
│   │   │   ├── kotlin/
│   │   │   │   └── org.smarthome/
│   │   │   │       ├── navigation/
│   │   │   │       │   └── NavHost.kt
│   │   │   │       ├── ui/
│   │   │   │       │   ├── components/
│   │   │   │       │   │   └── BottomNavBar.kt
│   │   │   │       │   ├── screens/
│   │   │   │       │   │   ├── HomeScreen.kt
│   │   │   │       │   │   └── LoginScreen.kt
│   │   │   │       │   └── theme/
│   │   │   │       │       ├── Theme.kt
│   │   │   │       │       └── App.kt
│   │   ├── desktopMain/
│   │   │   ├── kotlin/
│   │   │   │   └── org.smarthome/
│   │   │   │       └── main.kt
│   │   ├── wasmJsMain/
│   │   │   ├── kotlin/
│   │   │   │   └── org.smarthome/
│   │   │   │       └── main.kt
│   │   └── resources/
│   ├── build.gradle.kts
├── README.md
```

### Key Directories

* **`composeApp/`**: Contains the main application code.
    * **`src/`**: Source code directory.
        * **`androidMain/`**: Platform-specific code for Android.
            * **`kotlin/`**: Kotlin source files for Android.
                * **`org.smarthome/MainActivity.kt`**: Entry point for the Android application.
            * **`res/`**: Android resources (layouts, drawables, etc.).
                * **`AndroidManifest.xml`**: Android manifest file.
        * **`commonMain/`**: Shared Kotlin code across all platforms.
            * **`composeResources/`**: Shared resources for Compose Multiplatform (images, fonts, etc.).
            * **`kotlin/`**: Shared Kotlin source files.
                * **`org.smarthome/`**: Application package.
                    * **`navigation/NavHost.kt`**: Navigation setup using Compose Navigation.
                    * **`ui/`**: UI components and screens.
                        * **`components/BottomNavBar.kt`**: Bottom navigation bar component.
                        * **`screens/HomeScreen.kt`**: Home screen implementation.
                        * **`screens/LoginScreen.kt`**: Login screen implementation.
                        * **`theme/Theme.kt`**: Application theme definition.
                        * **`theme/App.kt`**: Root Compose application.
        * **`desktopMain/`**: Platform-specific code for Desktop.
            * **`kotlin/org.smarthome/main.kt`**: Entry point for the Desktop application.
        * **`wasmJsMain/`**: Platform-specific code for Web (WASM/JS).
            * **`kotlin/org.smarthome/main.kt`**: Entry point for the Web application.
        * **`resources/`**: Shared resources (non-Compose resources).
    * **`build.gradle.kts`**: Gradle build script for the `composeApp` module.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   cd Smart-Dash
   ```

2. **Open in IntelliJ IDEA or Android Studio:**
    * Import the `build.gradle.kts` file as a project.

3. **Set Up the Kotlin Multiplatform Plugin:**
    * Ensure the Kotlin Multiplatform Mobile plugin is installed and enabled.

4. **Run the Application:**
    * Select the desired run configuration (e.g., `composeApp - android`, `composeApp - desktop`,
      `composeApp - wasmJs`).
    * Click the run button.

## Development Guidelines

### Kotlin and Compose Multiplatform

* **Kotlin:** Smart-Dash is written in Kotlin. Familiarity with Kotlin syntax, coroutines, and functional programming is
  essential.
* **Compose Multiplatform:** The UI is built using Compose Multiplatform. Understand how to create composable functions,
  manage state, and handle platform differences.

### Working with the File System

* **`commonMain`:**
    * Place all shared code in `commonMain`. This includes UI components, business logic, and data models.
    * Use `composeResources` for shared images, fonts, and other assets.
* **Platform-Specific Directories (`androidMain`, `desktopMain`, `wasmJsMain`):**
    * Use these directories for platform-specific implementations and integrations.
    * Keep platform-specific code to a minimum to maximize code sharing.
* **Navigation:**
    * The `navigation/NavHost.kt` file manages navigation using Compose Navigation.
    * Define your navigation graph and routes here.
* **UI Components:**
    * Create reusable UI components in `ui/components`.
    * Follow a consistent UI pattern and style.
* **Screens:**
    * Implement application screens in `ui/screens`.
    * Separate UI logic from business logic.
* **Theme:**
    * Define the application theme in `ui/theme`.
    * Ensure consistent styling across all platforms.

### Best Practices

* **Code Sharing:** Maximize code sharing by placing logic and UI components in `commonMain`.
* **Platform Abstraction:** Use `expect`/`actual` declarations for platform-specific implementations.
* **Testing:** Write unit tests and UI tests for shared and platform-specific code.
* **Code Reviews:** Conduct regular code reviews to maintain code quality and consistency.
* **Documentation:** Document your code and provide clear commit messages.

## Dependencies

* **Compose Multiplatform:** For UI development.
* **Kotlin Coroutines:** For asynchronous programming.
* **Compose Navigation:** For navigation between screens.
* **Other libraries:** As specified in `build.gradle.kts`.

## Contributing

1. **Create a Branch:**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Changes:**
    * Follow the development guidelines.
    * Write tests for your changes.

3. **Commit Changes:**
   ```bash
   git add .
   git commit -m "Add your feature or fix"
   ```

4. **Push Changes:**
   ```bash
   git push origin feature/your-feature-name
   ```

5. **Create a Pull Request:**
    * Open a pull request on GitHub.
    * Provide a clear description of your changes.

## Troubleshooting

* **Build Issues:**
    * Clean and rebuild the project.
    * Invalidate caches and restart IntelliJ IDEA/Android Studio.
    * Check Gradle dependencies and versions.
* **Platform-Specific Issues:**
    * Ensure platform-specific SDKs are installed and configured correctly.
    * Check platform-specific logs for errors.