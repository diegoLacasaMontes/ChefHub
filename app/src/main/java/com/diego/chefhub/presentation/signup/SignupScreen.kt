package com.diego.chefhub.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.chefhub.R
import com.diego.chefhub.scaffold.MyBackTopAppBar
import com.diego.chefhub.ui.components.CustomButton
import com.diego.chefhub.ui.components.CustomInputField
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: SignupViewModel = viewModel(factory = SignupViewModelFactory(auth))
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { MyBackTopAppBar(navigateBack) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            SignupContent(
                uiState = uiState,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onSignupClick = { viewModel.signup(navigateToHome) },
                navigateToLogin = navigateToLogin
            )
        }
    }
}

@Composable
private fun SignupContent(
    uiState: SignupUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // EMAIL
        Text(text = "Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)

        CustomInputField(
            value = uiState.email,
            onValueChange = onEmailChange
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "You will have to confirm this address",
            color = White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(48.dp))

        // PASSWORD
        Text(text = "Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)

        CustomInputField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            password = true
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Use at least 10 characters",
            color = White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.weight(1f))

        // ERROR MESSAGE
        Text(
            text = uiState.errorMessage,
            color = if (uiState.errorMessage.isEmpty()) Color.Transparent else Color.Red,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        // SIGNUP BUTTON
        CustomButton(
            onClick = onSignupClick,
            title = "Create Account",
            image = R.drawable.email,
            transparent = false,
            enabled = uiState.isSignupEnabled && !uiState.isLoading
        )

        Spacer(Modifier.height(8.dp))

        // GOOGLE SIGNUP
        CustomButton(
            onClick = { /* TODO */ },
            title = "Continue with Google",
            image = R.drawable.google,
            transparent = true,
        )

        Spacer(Modifier.height(32.dp))

        // LOGIN LINK
        Text(text = "Already have an account?", color = White)

        Text(
            text = "Log In",
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.clickable { navigateToLogin() }
        )

        Spacer(Modifier.height(32.dp))
    }
}