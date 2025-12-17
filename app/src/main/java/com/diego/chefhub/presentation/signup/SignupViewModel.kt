package com.diego.chefhub.presentation.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel(private val auth: FirebaseAuth): ViewModel() {
    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    private val emailRegex = Regex("^[^@]+@[^@]+\\.[^@]+$")
    private val passwordRegex = Regex("^(?=.*[0-9])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{10,}$")

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            errorMessage = "",
            isSignupEnabled = validate(newEmail, _uiState.value.password)
        )
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(
            password = newPassword,
            errorMessage = "",
            isSignupEnabled = validate(_uiState.value.email, newPassword)
        )
    }

    private fun validate(email: String, password: String): Boolean {
        return emailRegex.matches(email) && passwordRegex.matches(password)
    }

    fun signup(onSuccess: () -> Unit) {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (!emailRegex.matches(email)) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Invalid email format"
            )
            return
        }

        if (!passwordRegex.matches(password)) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Password must contain a number and a special character"
            )
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                val exception = task.exception
                val message = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Password is too weak"
                    is FirebaseAuthInvalidCredentialsException -> "Invalid email"
                    is FirebaseAuthUserCollisionException -> "Email already in use"
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