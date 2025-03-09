package org.smarthome.navigation

import androidx.compose.runtime.*
import org.smarthome.ui.screens.HomeScreen
import org.smarthome.ui.screens.LoginScreen

@Composable
fun NavHost() {
    var currentScreen by remember { mutableStateOf("login") } // Manage screen state

    when (currentScreen) {
        "login" -> LoginScreen(onLoginSuccess = { currentScreen = "home" })
        "home" -> HomeScreen()
    }
}
