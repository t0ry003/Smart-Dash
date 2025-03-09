package org.smarthome

import androidx.compose.runtime.*
import org.smarthome.ui.screens.HomeScreen
import org.smarthome.ui.screens.LoginScreen

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("login") } // Track current screen

    if (currentScreen == "login") {
        LoginScreen(onLoginSuccess = { currentScreen = "home" }) // Navigate to home
    } else {
        HomeScreen()
    }
}
