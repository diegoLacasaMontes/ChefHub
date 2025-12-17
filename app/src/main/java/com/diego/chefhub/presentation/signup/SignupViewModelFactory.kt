package com.diego.chefhub.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class SignupViewModelFactory(
    private val auth: FirebaseAuth
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SignupViewModel(auth) as T
    }
}