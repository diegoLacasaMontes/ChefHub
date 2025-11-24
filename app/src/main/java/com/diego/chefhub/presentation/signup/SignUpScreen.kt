package com.diego.chefhub.presentation.signup

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun SignUpScreen(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateToLogIn: () -> Unit,
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
            SignUpContent(auth, navigateToHome, navigateToLogIn)
        }
    }
}

@Composable
fun SignUpContent(auth: FirebaseAuth, navigateToHome: () -> Unit, navigateToLogIn: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var enableSignUp by remember { mutableStateOf(false) }
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
                enableSignUp = emailRegex.matches(it) && passwordRegex.matches(password)
            }
        )
        Spacer(Modifier.height(8.dp)) // ¿Hacer el texto rojo cuando el email/contraseña no sean validos?
        Text("You will have to confirm this address", color = White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)

        Spacer(Modifier.height(48.dp))
        Text("Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        CustomInputField(
            value = password,
            onValueChange = { password = it },
            onValueChangeExtra = {
                enableSignUp = emailRegex.matches(email) && passwordRegex.matches(it)
            },
            password = true
        )
        Spacer(Modifier.height(8.dp))
        Text("Use at least 10 characters", color = White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)

        Spacer(Modifier.weight(1f))
        CustomButton(
            onClick = {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navigateToHome()
                        Log.i("diego", "SIGNUP OK")
                    } else {
                        Log.i("diego", "SIGNUP KO")
                    }
                }
            },
            title = "Create Account",
            image = R.drawable.email,
            transparent = false,
            enabled = enableSignUp
        )
        Spacer(Modifier.height(8.dp))
        CustomButton(
            onClick = {
                /* TODO */
                Log.i("diego", "Google")
            },
            title = "Continue with Google",
            image = R.drawable.google,
            transparent = true,
        )

        Spacer(Modifier.height(32.dp))

        Text(text = "Already have an account?", color = White)
        Text(
            text = "Log In",
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.clickable { navigateToLogIn() }
        )

        Spacer(Modifier.height(32.dp))
    }
}