package com.zako.webetu.auth.login.data.remote

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import com.zako.webetu.auth.login.data.LoginConstants
import com.zako.webetu.auth.login.model.LoginRequestBody
import com.zako.webetu.auth.login.model.LoginResponse
import com.zako.webetu.auth.login.model.RemoteDataSource
import com.zako.webetu.errors.AppError
import com.zako.webetu.errors.AuthenticationErrors
import com.zako.webetu.errors.ConnectionErrors
import com.zako.webetu.utils.BaseString
import com.zako.webetu.utils.parseResponse
import com.zako.webetu.utils.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class RemoteDataSourceImpl(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun login(
        registrationNumber: String,
        password: String
    ): Either<AppError, LoginResponse> {
        return client.safeCall {
            val url = LoginConstants.LOGIN_ENDPOINT
            val body = LoginRequestBody(
                username = registrationNumber,
                password = password
            )
            post(urlString = url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        }.flatMap { response: HttpResponse ->
            when (response.status.value) {
                HttpStatusCode.OK.value -> {
                    response.parseResponse<LoginResponse>()
                }
                HttpStatusCode.Forbidden.value -> {
                    AuthenticationErrors.AuthenticationException(
                        error = null
                    ).left()
                }
                else -> {
                    ConnectionErrors.ServerErrorException(
                        error = null,
                        errorMessage = BaseString.DynamicString(
                            response.bodyAsText()
                        )
                    ).left()
                }
            }
        }
    }
}
