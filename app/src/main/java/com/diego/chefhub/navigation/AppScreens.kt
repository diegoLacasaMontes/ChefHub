package com.diego.chefhub.navigation

sealed class AppScreens (val route: String) {
    data object LoginScreen: AppScreens(route = "LoginScreen")
    data object RegisterScreen: AppScreens(route = "RegisterScreen")
    data object PasswordRecoveryScreen: AppScreens(route = "PasswordRecoveryScreen")
    data object HomeScreen: AppScreens(route = "HomeScreen")
}