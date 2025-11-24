package com.diego.chefhub.presentation.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diego.chefhub.scaffold.MyHomeTopAppBar
import com.diego.chefhub.scaffold.MyNavigationBottomBar
import com.diego.chefhub.ui.theme.Black
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToCreate: () -> Unit,
    navigateToRecipe: () -> Unit,
    auth: FirebaseAuth,
    navigateToInitial: () -> Unit
) {
    Scaffold(
        topBar = { MyHomeTopAppBar() },
        bottomBar = { MyNavigationBottomBar(screen = "Account", navigateToHome = navigateToHome, navigateToSearch = navigateToSearch, navigateToCreate = navigateToCreate) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            AccountContent(navigateToRecipe, auth, navigateToInitial)
        }
    }
}

@Composable
fun AccountContent(navigateToRecipe: () -> Unit, auth: FirebaseAuth, navigateToInitial: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            auth.signOut()
            navigateToInitial()
        }) {
            Text("Cerrar sesión")
        }
    }
}