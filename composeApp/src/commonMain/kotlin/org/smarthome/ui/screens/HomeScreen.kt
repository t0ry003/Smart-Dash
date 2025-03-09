package org.smarthome.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
@Preview
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var showAddDeviceDialog by remember { mutableStateOf(false) }
    var devices by remember { mutableStateOf<List<Device>>(emptyList()) }

    Scaffold(
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(
                    onClick = { showAddDeviceDialog = true },
                    backgroundColor = Color(126, 138, 151)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Device")
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color(231, 222, 200),
                contentColor = Color(48, 71, 94)
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
                    label = { Text("Favorite") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> HomeContent(innerPadding, devices) { name, ip ->
                devices = devices + Device(name, ip)
                showAddDeviceDialog = false
            }

            1 -> FavoriteContent(innerPadding)
            else -> SettingsContent(innerPadding)
        }
    }

    if (showAddDeviceDialog) {
        AddDeviceDialog(
            onDismiss = { showAddDeviceDialog = false },
            onAddDevice = { name, ip ->
                devices = devices + Device(name, ip)
                showAddDeviceDialog = false
            }
        )
    }
}

@Composable
fun HomeContent(innerPadding: PaddingValues, devices: List<Device>, onAddDevice: (String, String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(48, 71, 94))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            if (devices.isEmpty()) {
                item {
                    Text(
                        text = "No devices available.",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(devices.take(30)) { device ->
                    DeviceCard(device)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AddDeviceDialog(onDismiss: () -> Unit, onAddDevice: (String, String) -> Unit) {
    var deviceName by remember { mutableStateOf("") }
    var deviceIp by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xFF1C1C1C)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Add Device",
                    fontSize = 20.sp,
                    color = Color.White
                )
                OutlinedTextField(
                    value = deviceName,
                    onValueChange = { deviceName = it },
                    label = { Text("Device Name", color = Color(0xFFAAAAAA)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color(0xFFBBBBBB),
                        unfocusedBorderColor = Color(0xFF666666)
                    )
                )
                OutlinedTextField(
                    value = deviceIp,
                    onValueChange = { deviceIp = it },
                    label = { Text("Device IP", color = Color(0xFFAAAAAA)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color(0xFFBBBBBB),
                        unfocusedBorderColor = Color(0xFF666666)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        if (deviceName.isNotBlank() && deviceIp.isNotBlank()) {
                            onAddDevice(deviceName, deviceIp)
                        }
                    }) {
                        Text("Add", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceCard(device: Device) {
    var isOn by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFF2C2C2C)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = device.name,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = device.ip,
                fontSize = 12.sp,
                color = Color(0xFFAAAAAA)
            )
            Switch(
                checked = isOn,
                onCheckedChange = { isOn = it },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                    uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
                )
            )
        }
    }
}


@Composable
fun FavoriteContent(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Favorite Screen",
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Composable
fun SettingsContent(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings Screen",
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

data class Device(
    val name: String,
    val ip: String
)
