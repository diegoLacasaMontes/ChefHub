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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

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
private fun SignUpContent(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateToLogIn: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var enableSignUp by remember { mutableStateOf(false) }

    val emailRegex = Regex("^[^@]+@[^@]+\\.[^@]+$")
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{10,}$")

    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // EMAIL
        Text(text = "Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)

        CustomInputField(
            value = email,
            onValueChange = { email = it },
            onValueChangeExtra = {
                enableSignUp = emailRegex.matches(it) && passwordRegex.matches(password)
                errorMessage = ""
            }
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
            value = password,
            onValueChange = { password = it },
            onValueChangeExtra = {
                enableSignUp = emailRegex.matches(email) && passwordRegex.matches(it)
                errorMessage = ""
            },
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
            text = errorMessage,
            color = if (errorMessage.isEmpty()) Color.Transparent else Color.Red,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        // BUTTON SIGN UP
        CustomButton(
            onClick = {
                if (!emailRegex.matches(input = email)) {
                    errorMessage = "Invalid email format"
                    return@CustomButton
                }

                if (!passwordRegex.matches(input = password)) {
                    errorMessage = "Password must contain a number and a special character"
                    return@CustomButton
                }

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navigateToHome()
                    } else {
                        val exception = task.exception
                        errorMessage = when (exception) {
                            is FirebaseAuthWeakPasswordException -> "Password is too weak"
                            is FirebaseAuthInvalidCredentialsException -> "Invalid email"
                            is FirebaseAuthUserCollisionException -> "Email already in use"
                            else -> exception?.message ?: "Unknown error"
                        }
                    }
                }
            },
            title = "Create Account",
            image = R.drawable.email,
            transparent = false,
            enabled = enableSignUp
        )

        Spacer(Modifier.height(8.dp))

        // GOOGLE BUTTON
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

        // LOGIN LINK
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