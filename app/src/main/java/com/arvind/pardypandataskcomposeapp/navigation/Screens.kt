package com.arvind.pardypandataskcomposeapp.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login_scrren")
    object HomeScreen : Screens("home_screen")
}