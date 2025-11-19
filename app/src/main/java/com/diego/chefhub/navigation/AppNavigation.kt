package com.diego.chefhub.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.chefhub.presentation.initial.InitialScreen
import com.diego.chefhub.presentation.login.LogInScreen
import com.diego.chefhub.presentation_tutorial.home.HomeScreenViejo
import com.diego.chefhub.presentation.signup.SignUpScreen
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
        AppScreens.HomeScreenViejo.route
    } else {
        AppScreens.InitialScreen.route
    }

    // COMENTARIO.
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = AppScreens.HomeScreenViejo.route) {
            HomeScreenViejo(
                auth,
                navigateToInitial = { navController.navigate(AppScreens.InitialScreen.route) }
            )
        }


        composable(route = AppScreens.InitialScreen.route) {
            InitialScreen(
                navigateToLogIn = { navController.navigate(AppScreens.LogInScreen.route) },
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.route) }
            )
        }
        composable(route = AppScreens.LogInScreen.route) {
            LogInScreen(
                auth,
                navigateBack = { navController.navigate(AppScreens.InitialScreen.route) },
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.route) },
                navigateToHome = { navController.navigate(AppScreens.HomeScreenViejo.route) }
            )
        }
        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen(
                auth,
                navigateToHome = { navController.navigate(AppScreens.HomeScreenViejo.route) },
                navigateToLogIn = { navController.navigate(AppScreens.LogInScreen.route) },
                navigateBack = { navController.navigate(AppScreens.InitialScreen.route) }
                )
        }
    }
}