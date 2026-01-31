package com.example.template

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.template.ui.theme.TemplateAppTheme

@Composable
fun App() {
    val navController = rememberNavController()

    TemplateAppTheme {
        NavHost(navController = navController, startDestination = "counter") {
            composable("counter") {
                CounterScreen(
                    onGoToSettings = { navController.navigate("settings") }
                )
            }
            composable("settings") {
                SettingsScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}