package com.diego.chefhub.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.chefhub.presentation_tutorial.home.HomeScreen2
import com.diego.chefhub.presentation_tutorial.initial.InitialScreen
import com.diego.chefhub.presentation_tutorial.login.LogInScreen
import com.diego.chefhub.presentation_tutorial.signup.SignUpScreen
import com.diego.chefhub.screens.HomeScreen
import com.diego.chefhub.screens.LoginScreen
import com.diego.chefhub.screens.PasswordRecoveryScreen
import com.diego.chefhub.screens.RegisterScreen
import com.diego.chefhub.ui.AppViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    // COMENTARIO.
//    val context = LocalContext.current
    val navController = rememberNavController()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val appViewModel: AppViewModel = viewModel()

    val startDestination = if (currentUser != null) {
        AppScreens.HomeScreen2.route
    } else {
        AppScreens.InitialScreen.route
    }

    // COMENTARIO.
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = AppScreens.LoginScreen.route) { LoginScreen(navController, appViewModel) }
        composable(route = AppScreens.RegisterScreen.route) { RegisterScreen(navController, appViewModel) }
        composable(route = AppScreens.PasswordRecoveryScreen.route) { PasswordRecoveryScreen(navController, appViewModel) }
        composable(route = AppScreens.HomeScreen.route) { HomeScreen(navController, appViewModel, auth) }


        composable(route = AppScreens.InitialScreen.route) { InitialScreen(
            navigateToLogIn = { navController.navigate(AppScreens.LogInScreen2.route) },
            navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.route) }
        ) }
        composable(route = AppScreens.LogInScreen2.route) { LogInScreen(auth) }
        composable(route = AppScreens.SignUpScreen.route) { SignUpScreen(auth) }
        composable(route = AppScreens.HomeScreen2.route) { HomeScreen2() }
    }
}