package org.smarthome.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.smarthome.isWeb

@Composable
fun topBar(
    title: String = "Smart-Dash",
    onMenuClick: (String) -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        actions = {
            // Menu Button
            IconButton(onClick = { isMenuExpanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }

            // Menu Dropdown
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                DropdownMenuItem(content = { Text("Settings") }, onClick = {
                    isMenuExpanded = false
                    onMenuClick("Settings")
                })
                DropdownMenuItem(content = { Text("About") }, onClick = {
                    isMenuExpanded = false
                    onMenuClick("About")
                })
                if(!isWeb()) {
                    DropdownMenuItem(content = { Text("Exit") }, onClick = {
                        isMenuExpanded = false
                        onMenuClick("Exit")
                    })
                }
            }
        }, modifier = Modifier.fillMaxWidth()
    )
}