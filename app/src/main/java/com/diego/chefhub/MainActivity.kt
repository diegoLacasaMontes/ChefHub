package com.diego.chefhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.chefhub.navigation.AppNavigation
import com.diego.chefhub.ui.AppViewModel
import com.diego.chefhub.ui.theme.ChefHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ChefHubTheme() {
                AppNavigation()
            }
        }
    }
}