package com.diego.chefhub.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModelFactory(
    private val auth: FirebaseAuth
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(auth) as T
    }
}