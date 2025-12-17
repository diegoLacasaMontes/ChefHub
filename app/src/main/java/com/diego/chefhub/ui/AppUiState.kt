package com.diego.chefhub.ui

data class AppUiState (
    val themeOptions: List<String> = listOf("Light", "Dark"),

    val languageOptions: Map<String, String> = mapOf(
        "English" to "en",
        "Español" to "es",
        "Français" to "fr",
        "Italiano" to "it",
        "中国人" to "zh",
        "日本語" to "ja"
    ),

    val selectedTheme: String = "Dark",

    val selectedLanguage: String = "English",
    val selectedLanguageCode: String = "en"
)