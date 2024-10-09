package com.android.compose.exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.compose.exercise.ui.MainScreen
import com.android.compose.exercise.ui.theme.ExerciseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExerciseTheme {
                MainScreen { finish() }
            }
        }
    }

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_SEARCH = "search"
    }
}
