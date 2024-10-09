package com.android.compose.exercise.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.compose.exercise.MainActivity.Companion.ROUTE_HOME
import com.android.compose.exercise.MainActivity.Companion.ROUTE_SEARCH

@Composable
fun MainScreen(
    finish: () -> Unit
) {
    val isHomeSelected = rememberSaveable { mutableStateOf(true) }
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                isHomeSelected.value,
                onHomeSelected = {
                    isHomeSelected.value = true
                    navController.navigate(ROUTE_HOME)
                },
                onSearchSelected = {
                    isHomeSelected.value = false
                    navController.navigate(ROUTE_SEARCH)
                },
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController,
                startDestination = ROUTE_HOME,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                composable(ROUTE_HOME) { HomeView { finish() } }
                composable(ROUTE_SEARCH) { SearchView { finish() } }
            }
        }
    }
}
