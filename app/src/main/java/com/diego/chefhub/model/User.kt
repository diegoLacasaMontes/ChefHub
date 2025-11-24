package com.diego.chefhub.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val image: String,
    val bio: String,
    val isActive: Boolean = true,
    val createdAt:String,
    val updatedAt: String
)