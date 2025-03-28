package com.zako.webetu.auth.login.presentation

data class LoginScreenState(
    val registrationNumber: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
)
