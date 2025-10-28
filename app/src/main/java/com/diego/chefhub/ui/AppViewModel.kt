package com.diego.chefhub.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class AppViewModel(): ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    /** User functions **/
    fun <T> onUserChanged(newValue: T, valueName: String) {
        // COMENTARIO.
        val currentState = appUiState.value

        // COMENTARIO.
        val updatedState = when (valueName) {
            "userName" -> if (newValue is String) currentState.copy(user = newValue) else currentState
            "email" -> if (newValue is String) currentState.copy(email = newValue) else currentState
            "password" -> if (newValue is String) currentState.copy(paswword = newValue) else currentState
            else -> currentState
        }

        // COMENTARIO.
        if (updatedState != currentState) {
            _appUiState.update { updatedState }
        }
    }

    fun resetUserValues() {
        _appUiState.update { currentState ->
            currentState.copy(
                user = "",
                email = "",
                paswword = ""
            )
        }
    }


    /** Login functions **/
    fun checkLogin(callback: (Int) -> Unit) {
        val auth: FirebaseAuth = Firebase.auth
        val email = appUiState.value.email
        val password = appUiState.value.paswword
        var tries = appUiState.value.tries

        // COMENTARIO.
        if (email.isEmpty() || password.isEmpty()) {
            callback(1) // 1: Algunos campos estan vacíos.
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(0) // 0: Inicio de sesión exitoso.
            } else {
                when (task.exception) {
                    is FirebaseAuthInvalidUserException -> callback(2) // 2: Correo no encontrado en Firebase.
                    is FirebaseAuthInvalidCredentialsException -> {
                        tries--

                        _appUiState.update { currentState ->
                            currentState.copy(tries = tries)
                        }

                        callback(3)
                    } // 3: Contraseña incorrecta.
                    else -> callback(4) // 4: Error inesperado.
                }
            }
        }
    }
}