package com.example.swipe.navigation

import java.lang.IllegalArgumentException

enum class ScreenNavigation {
    HomeScreen,
    EditScreen;

    companion object {
        fun fromRoute(route: String?): ScreenNavigation = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            EditScreen.name -> EditScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("route $route is not recognize")
        }
    }
}