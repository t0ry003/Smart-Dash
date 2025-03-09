package org.smarthome.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }

    // Test credentials
    val testUsername = "admin"
    val testPassword = "admin"

    // Handle login logic
    LaunchedEffect(isLoading) {
        if (isLoading) {
            // Simulate a network call or database check
            delay(800)
            if (username == testUsername && password == testPassword) {
                onLoginSuccess()
            } else {
                error = true
            }
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(48, 71, 94))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Title
        Text(
            text = "Smart-Dash",
            fontSize = 32.sp,
            color = Color(231, 222, 200),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color(0xFFAAAAAA)) },
            singleLine = true,
            modifier = Modifier
                .width(280.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color(231, 222, 200),
                cursorColor = Color(231, 222, 200),
                focusedBorderColor = Color(203, 175, 135),
                unfocusedBorderColor = Color(231, 222, 200)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color(0xFFAAAAAA)) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .width(280.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color(231, 222, 200),
                cursorColor = Color(231, 222, 200),
                focusedBorderColor = Color(203, 175, 135),
                unfocusedBorderColor = Color(231, 222, 200)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    error = true
                } else {
                    isLoading = true
                    error = false
                }
            },
            modifier = Modifier
                .width(280.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(126, 138, 151)
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color(231, 222, 200),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login", color = Color(231, 222, 200))
            }
        }

        // Error Message
        if (error) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (username.isBlank() || password.isBlank()) {
                    "Please enter both username and password"
                } else {
                    "Invalid credentials. Use: $testUsername / $testPassword"
                },
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}