package com.diego.chefhub.presentation.signup

data class SignupUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isSignupEnabled: Boolean = false
)