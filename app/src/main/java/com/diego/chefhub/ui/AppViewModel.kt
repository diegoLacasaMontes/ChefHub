package com.diego.chefhub.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class AppViewModel(): ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    /** App functions **/
    fun changeTheme(newTheme: String) {
        _appUiState.value = _appUiState.value.copy(
            selectedTheme = newTheme
        )
        Log.i("Diego", "Theme changed: $newTheme")
    }

    fun changeLanguage(newLanguage: String) {
        // TODO
    }
}