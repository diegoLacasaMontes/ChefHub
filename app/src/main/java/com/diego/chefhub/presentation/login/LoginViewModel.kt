package com.diego.chefhub.presentation.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(private val auth: FirebaseAuth): ViewModel() {
    private val _uiState = MutableStateFlow(value = LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val emailRegex = Regex(pattern = "^[^@]+@[^@]+\\.[^@]+$")
    private val passwordRegex = Regex(pattern = "^(?=.*[0-9])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{10,}$")

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            errorMessage = "",
            isLoginEnabled = validate(newEmail, password = _uiState.value.password)
        )
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(
            password = newPassword,
            errorMessage = "",
            isLoginEnabled = validate(email = _uiState.value.email, newPassword)
        )
    }

    private fun validate(email: String, password: String): Boolean {
        return emailRegex.matches(input = email) && passwordRegex.matches(input = password)
    }

    fun login(onSuccess: () -> Unit) {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (!emailRegex.matches(input = email)) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Invalid email format"
            )
            return
        }

        if (!passwordRegex.matches(input = password)) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Password must contain a number and a special character"
            )
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                val exception = task.exception
                val message = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect email or password"
                    is FirebaseAuthInvalidUserException -> "This account does not exist"
                    else -> exception?.message ?: "Unknown error"
                }
                _uiState.value = _uiState.value.copy(
                    errorMessage = message,
                    isLoading = false
                )
            }
        }
    }
}