package com.diego.chefhub.presentation.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diego.chefhub.R
import com.diego.chefhub.presentation.signup.components.CustomButton
import com.diego.chefhub.presentation.signup.components.CustomInputField
import com.diego.chefhub.scaffold.MyBackTopAppBar
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogInScreen(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = { MyBackTopAppBar(navigateBack) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            LogInContent(auth, navigateToHome, navigateToSignUp)
        }
    }
}

@Composable
fun LogInContent(auth: FirebaseAuth, navigateToHome: () -> Unit, navigateToSignUp: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var enableLogIn by remember { mutableStateOf(false) }
    val emailRegex = Regex("^[^@]+@[^@]+\\.[^@]+$")
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{10,}$")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        CustomInputField(
            value = email,
            onValueChange = { email = it },
            onValueChangeExtra = {
                enableLogIn = emailRegex.matches(it) && passwordRegex.matches(password)
            }
        )
        Spacer(Modifier.height(48.dp))

        Text("Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        CustomInputField(
            value = password,
            onValueChange = { password = it },
            onValueChangeExtra = {
                enableLogIn = emailRegex.matches(email) && passwordRegex.matches(it)
            },
            password = true
        )

        Spacer(Modifier.weight(1f))
        CustomButton(
            onClick = {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navigateToHome()
                        Log.i("diego", "LOGIN OK")
                    } else {
                        Log.i("diego", "LOGIN KO")
                    }
                }
            },
            title = "Log In",
            image = R.drawable.email,
            transparent = false,
            enabled = enableLogIn
        )
        Spacer(Modifier.height(8.dp))
        CustomButton(
            onClick = {
                /* TODO */
                Log.i("diego", "Google")
            },
            title = "Continue with Google",
            image = R.drawable.google,
            transparent = true
        )

        Spacer(Modifier.height(32.dp))

        Text(text = "Don't have an account?", color = White)
        Text(
            text = "Sign Up",
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.clickable { navigateToSignUp() }
        )

        Spacer(Modifier.height(32.dp))
    }
}