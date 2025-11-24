package com.diego.chefhub.model

data class Recipe(
    val id: Int,
    val userId: String,
    val category: String,
    val title: String,
    val description: String,
    val dificulty: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: String,
    val preparationTime: Int,
    val cookingTime: Int,
    val servings: Int,
    val createdAt: String,
    val updatedAt: String
)