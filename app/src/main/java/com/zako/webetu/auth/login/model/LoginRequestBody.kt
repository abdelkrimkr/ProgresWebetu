package com.zako.webetu.auth.login.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestBody(
    val username : String ,
    val password : String
)
