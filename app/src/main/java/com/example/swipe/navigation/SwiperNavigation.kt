package com.example.swipe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swipe.screen.EditScreen
import com.example.swipe.screen.HomeScreen

@Composable
fun SwipeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.HomeScreen.name
    ) {
        composable(ScreenNavigation.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(ScreenNavigation.EditScreen.name) {
            EditScreen(navController = navController)
        }
    }
}