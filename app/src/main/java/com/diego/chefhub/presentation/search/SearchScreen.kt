package com.diego.chefhub.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diego.chefhub.scaffold.MyHomeTopAppBar
import com.diego.chefhub.scaffold.MyNavigationBottomBar

@Composable
fun SearchScreen(
    navigateToHome: () -> Unit,
    navigateToCreate: () -> Unit,
    navigateToAccount: () -> Unit,
    navigateToRecipe: () -> Unit,
) {
    Scaffold(
        topBar = { MyHomeTopAppBar() },
        bottomBar = {
            MyNavigationBottomBar(
                screen = "Search",
                navigateToHome = navigateToHome,
                navigateToCreate = navigateToCreate,
                navigateToAccount = navigateToAccount
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            SearchContent(navigateToRecipe)
        }
    }
}

@Composable
private fun SearchContent(navigateToRecipe: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Search screen", color = MaterialTheme.colorScheme.onBackground)
    }
}