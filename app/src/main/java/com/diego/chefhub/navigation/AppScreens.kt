package com.diego.chefhub.navigation

sealed class AppScreens (val route: String) {
    data object InitialScreen: AppScreens(route = "InitialScreen")
    data object LogInScreen: AppScreens(route = "LogInScreen2")
    data object SignUpScreen: AppScreens(route = "SignUpScreen")
    data object HomeScreen: AppScreens(route = "HomeScreen")
    data object SearchScreen: AppScreens(route = "SearchScreen")
    data object CreateScreen: AppScreens(route = "CreateScreen")
    data object AccountScreen: AppScreens(route = "AccountScreen")
    data object SettingsScreen: AppScreens(route = "SettingsScreen")
}