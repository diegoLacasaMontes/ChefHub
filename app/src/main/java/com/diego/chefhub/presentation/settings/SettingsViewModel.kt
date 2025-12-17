package com.diego.chefhub.presentation.settings

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel(private val auth: FirebaseAuth): ViewModel() {
    fun logout(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
    }

    fun deleteAccount(onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.currentUser?.delete()
            ?.addOnSuccessListener { onSuccess() }
            ?.addOnFailureListener { e -> onError(e.message ?: "Error deleting account") }
    }
}