package com.diego.chefhub.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.chefhub.R
import com.diego.chefhub.scaffold.MyBackTopAppBar
import com.diego.chefhub.ui.components.CustomButton
import com.diego.chefhub.ui.components.CustomInputField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(auth))
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { MyBackTopAppBar(navigateBack) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            LoginContent(
                uiState = uiState,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLoginClick = { viewModel.login(onSuccess = navigateToHome) },
                navigateToSignup = navigateToSignup
            )
        }
    }
}

@Composable
private fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    navigateToSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // EMAIL
        Text(text = "Email", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold, fontSize = 40.sp)

        CustomInputField(
            value = uiState.email,
            onValueChange = onEmailChange
        )

        Spacer(Modifier.height(height = 48.dp))

        // PASSWORD
        Text(text = "Password", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold, fontSize = 40.sp)

        CustomInputField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            password = true
        )

        Spacer(Modifier.weight(weight = 1f))

        // ERROR MESSAGE
        Text(
            text = uiState.errorMessage,
            color = if (uiState.errorMessage.isEmpty()) Color.Transparent else Color.Red,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(height = 8.dp))

        // LOGIN BUTTON
        CustomButton(
            onClick = onLoginClick,
            title = "Log In",
            image = R.drawable.email,
            enabled = uiState.isLoginEnabled && !uiState.isLoading
        )

        Spacer(Modifier.height(height = 8.dp))

        // GOOGLE LOGIN
        CustomButton(
            onClick = { /* TODO */ },
            title = "Continue with Google",
            image = R.drawable.google,
            transparent = true
        )

        Spacer(Modifier.height(height = 32.dp))

        // SIGNUP LINK
        Text(text = "Don't have an account?", color = MaterialTheme.colorScheme.onBackground)

        Text(
            text = "Sign Up",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable { navigateToSignup() }
        )

        Spacer(Modifier.height(height = 32.dp))
    }
}