package com.android.compose.exercise.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    isHomeSelected: Boolean = true,
    onHomeSelected: () -> Unit,
    onSearchSelected: () -> Unit
) {
    BottomAppBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = isHomeSelected,
            onClick = { onHomeSelected() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text("Search") },
            selected = !isHomeSelected,
            onClick = { onSearchSelected() }
        )
    }
}
