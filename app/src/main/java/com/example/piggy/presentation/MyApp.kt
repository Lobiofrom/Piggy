package com.example.piggy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp(
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable(route = "mainScreen") {
            MainScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(route = "addScreen") {
            AddScreen(
                navController = navController
            )
        }
    }
}