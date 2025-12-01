package com.diego.chefhub.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.chefhub.presentation.account.AccountScreen
import com.diego.chefhub.presentation.create.CreateScreen
import com.diego.chefhub.presentation.home.HomeScreen
import com.diego.chefhub.presentation.initial.InitialScreen
import com.diego.chefhub.presentation.login.LogInScreen
import com.diego.chefhub.presentation.search.SearchScreen
import com.diego.chefhub.presentation.settings.SettingsScreen
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
        AppScreens.HomeScreen.route
    } else {
        AppScreens.InitialScreen.route
    }

    // COMENTARIO.
    NavHost(navController = navController, startDestination = startDestination) {
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
                navigateToHome = { navController.navigate(AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen(
                auth,
                navigateToHome = { navController.navigate(AppScreens.HomeScreen.route) },
                navigateToLogIn = { navController.navigate(AppScreens.LogInScreen.route) },
                navigateBack = { navController.navigate(AppScreens.InitialScreen.route) }
            )
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                navigateToSearch = { navController.navigate(AppScreens.SearchScreen.route) },
                navigateToCreate = { navController.navigate(AppScreens.CreateScreen.route) },
                navigateToAccount = { navController.navigate(AppScreens.AccountScreen.route) },
                navigateToRecipe = {},
            )
        }
        composable(route = AppScreens.SearchScreen.route) {
            SearchScreen(
                navigateToHome = { navController.navigate(AppScreens.HomeScreen.route) },
                navigateToCreate = { navController.navigate(AppScreens.CreateScreen.route) },
                navigateToAccount = { navController.navigate(AppScreens.AccountScreen.route) },
                navigateToRecipe = {},
            )
        }
        composable(route = AppScreens.CreateScreen.route) {
            CreateScreen(
                navigateToHome = { navController.navigate(AppScreens.HomeScreen.route) },
                navigateToSearch = { navController.navigate(AppScreens.SearchScreen.route) },
                navigateToAccount = { navController.navigate(AppScreens.AccountScreen.route) },
            )
        }
        composable(route = AppScreens.AccountScreen.route) {
            AccountScreen(
                navigateToHome = { navController.navigate(AppScreens.HomeScreen.route) },
                navigateToSearch = { navController.navigate(AppScreens.SearchScreen.route) },
                navigateToCreate = { navController.navigate(AppScreens.CreateScreen.route) },
                navigateToRecipe = {},
                navigateToSettings = { navController.navigate(AppScreens.SettingsScreen.route) }
            )
        }
        composable(route = AppScreens.SettingsScreen.route) {
            SettingsScreen(
                navigateToAccount = { navController.navigate(AppScreens.AccountScreen.route) },
                auth = auth,
                navigateToInitial = { navController.navigate(AppScreens.InitialScreen.route) }
            )
        }
    }
}