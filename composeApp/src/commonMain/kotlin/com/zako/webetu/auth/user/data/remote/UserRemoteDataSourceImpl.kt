package com.zako.webetu.auth.user.data.remote


import com.zako.webetu.auth.user.model.UserRemoteDataSource
import io.ktor.client.HttpClient

class UserRemoteDataSourceImpl(
    private val client : HttpClient
) : UserRemoteDataSource {

}