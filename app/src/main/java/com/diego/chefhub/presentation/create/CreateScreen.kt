package com.diego.chefhub.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diego.chefhub.scaffold.MyHomeTopAppBar
import com.diego.chefhub.scaffold.MyNavigationBottomBar
import com.diego.chefhub.ui.theme.Light_Black
import com.diego.chefhub.ui.theme.White

@Composable
fun CreateScreen(
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToAccount: () -> Unit
) {
    Scaffold(
        topBar = { MyHomeTopAppBar() },
        bottomBar = {
            MyNavigationBottomBar(
                screen = "Create",
                navigateToHome = navigateToHome,
                navigateToSearch = navigateToSearch,
                navigateToAccount = navigateToAccount
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Light_Black)
        ) {
            CreateContent()
        }
    }
}

@Composable
private fun CreateContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Create screen", color = White)
    }
}