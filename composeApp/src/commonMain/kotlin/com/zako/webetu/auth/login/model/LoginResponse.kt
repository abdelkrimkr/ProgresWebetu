package com.zako.webetu.auth.login.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val expirationDate: String,
    val token: String,
    val userId: Int,
    val uuid: String,
    val idIndividu: Int,
    val etablissementId: Int,
    val userName: String
)
