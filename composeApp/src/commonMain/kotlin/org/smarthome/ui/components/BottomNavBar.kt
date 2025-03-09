package org.smarthome.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(0xFF1E1E1E),
        contentColor = Color.White,
        elevation = 8.dp
    ){
        val items = listOf("Home" to Icons.Outlined.Home, "Devices" to Icons.Outlined.Home, "Settings" to Icons.Outlined.Settings)

        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.second, contentDescription = item.first) },
                label = { Text(item.first) },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray
            )
        }
    }
}