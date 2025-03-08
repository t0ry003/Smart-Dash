package org.smarthome

import org.smarthome.components.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import smart_dash.composeapp.generated.resources.Res
import smart_dash.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("Home") }

    val context = getExitContext()

    MaterialTheme {
        Scaffold(
            topBar = {
                topBar(title = "Smart-Dash") { screen ->
                    when (screen) {
                        "Exit" -> exitApp(context)
                        else -> currentScreen = screen
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                when (currentScreen) {
                    "Home" -> Text("Welcome to Smart-Dash!", style = MaterialTheme.typography.h3)
                    "Settings" -> Text("Settings Screen", style = MaterialTheme.typography.h3)
                    "About" -> Text("About Smart-Dash", style = MaterialTheme.typography.h3)
                }
            }
        }
    }
}