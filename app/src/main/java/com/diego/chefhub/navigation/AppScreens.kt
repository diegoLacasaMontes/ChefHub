package com.diego.chefhub.navigation

sealed class AppScreens (val route: String) {
    data object RegisterScreen: AppScreens(route = "RegisterScreen")
    data object PasswordRecoveryScreen: AppScreens(route = "PasswordRecoveryScreen")


    data object InitialScreen: AppScreens(route = "InitialScreen")
    data object LogInScreen: AppScreens(route = "LogInScreen2")
    data object SignUpScreen: AppScreens(route = "SignUpScreen")
    data object HomeScreen: AppScreens(route = "HomeScreen")
    data object AccountScreen: AppScreens(route = "AccountScreen")
}