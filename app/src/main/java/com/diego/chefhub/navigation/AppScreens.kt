package com.diego.chefhub.navigation

sealed class AppScreens (val route: String) {
    data object LoginScreen: AppScreens(route = "LoginScreen")
    data object RegisterScreen: AppScreens(route = "RegisterScreen")
    data object PasswordRecoveryScreen: AppScreens(route = "PasswordRecoveryScreen")
    data object HomeScreen: AppScreens(route = "HomeScreen")


    data object InitialScreen: AppScreens(route = "InitialScreen")
    data object LogInScreen2: AppScreens(route = "LogInScreen2")
    data object SignUpScreen: AppScreens(route = "SignUpScreen")
    data object HomeScreen2: AppScreens(route = "HomeScreen2")
}