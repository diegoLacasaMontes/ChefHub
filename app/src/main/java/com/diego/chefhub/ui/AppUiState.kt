package com.diego.chefhub.ui

data class AppUiState (
    /** General variables **/
    val tries: Int = 3,

    /** User-related variables **/
    val user: String = "",
    val email: String = "",
    val paswword: String = "",
)