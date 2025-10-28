package com.diego.chefhub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.chefhub.screens.HomeScreen
import com.diego.chefhub.screens.LoginScreen
import com.diego.chefhub.screens.PasswordRecoveryScreen
import com.diego.chefhub.screens.RegisterScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.chefhub.ui.AppViewModel

@Composable
fun AppNavigation() {
    // COMENTARIO.
    val context = LocalContext.current
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()

    // COMENTARIO.
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) { LoginScreen(navController, appViewModel) }
        composable(route = AppScreens.RegisterScreen.route) { RegisterScreen(navController, appViewModel) }
        composable(route = AppScreens.PasswordRecoveryScreen.route) { PasswordRecoveryScreen(navController, appViewModel) }
        composable(route = AppScreens.HomeScreen.route) { HomeScreen(navController, appViewModel) }
    }
}