package com.example.taskmaster.screen.auth

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isSignUp: Boolean = true,
)
