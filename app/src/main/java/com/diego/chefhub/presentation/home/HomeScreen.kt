package com.diego.chefhub.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diego.chefhub.scaffold.MyHomeTopAppBar
import com.diego.chefhub.scaffold.MyNavigationBottomBar
import com.diego.chefhub.ui.theme.Black

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToCreate: () -> Unit,
    navigateToAccount: () -> Unit,
    navigateToRecipe: () -> Unit,
) {
    Scaffold(
        topBar = { MyHomeTopAppBar() },
        bottomBar = { MyNavigationBottomBar(screen = "Home", navigateToSearch = navigateToSearch, navigateToCreate = navigateToCreate, navigateToAccount = navigateToAccount) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            HomeContent(navigateToRecipe)
        }
    }
}

@Composable
fun HomeContent(navigateToRecipe: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        //
    }
}