package com.arvind.pardypandataskcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arvind.pardypandataskcomposeapp.screens.HomeScreen
import com.arvind.pardypandataskcomposeapp.screens.LoginScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }

    }
}
