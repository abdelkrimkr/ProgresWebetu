package com.zako.webetu.utils

import arrow.core.Either
import com.zako.webetu.errors.AppError
import com.zako.webetu.errors.AuthenticationErrors
import com.zako.webetu.errors.ConnectionErrors
import com.zako.webetu.errors.DataProcessingErrors
import com.zako.webetu.errors.UnknownError
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException

suspend fun HttpClient.safeCall(
    call: suspend HttpClient.() -> HttpResponse
): Either<AppError, HttpResponse> {
    return Either.catch {
        call()
    }.mapLeft { e ->
        when (e) {
            is SocketException -> ConnectionErrors.NoInternetConnection(error = e)
            is UnknownHostException -> ConnectionErrors.NoInternetConnection(error = e)
            is HttpRequestTimeoutException -> ConnectionErrors.TimeoutError(error = e)
            is javax.net.ssl.SSLHandshakeException -> ConnectionErrors.SSLHandshakeError(error = e)
            is IOException -> ConnectionErrors.NoInternetConnection(error = e)
            is UnresolvedAddressException -> ConnectionErrors.NoInternetConnection(error = e)
            else -> UnknownError(error = e)
        }
    }
}

@Suppress("ThrowsCount")
suspend inline fun <reified T> HttpResponse.parseResponse(): Either<AppError, T> {
    return Either.catch {
        when (status.value) {
            in 200..299 -> {
                try {
                    val serializer = Json { ignoreUnknownKeys = true }
                    serializer.decodeFromString<T>(bodyAsText())
                } catch (e: SerializationException) {
                    throw DataProcessingErrors.DataSerializationException(error = e)
                }
            }
            401 -> {
                throw AuthenticationErrors.AuthenticationException(
                    errorMessage = BaseString.DynamicString("status code : $status , body : ${this.bodyAsText()}"),
                    error = null
                )
            }
            in 500..599 -> {
                throw ConnectionErrors.ServerErrorException(
                    errorMessage = BaseString.DynamicString("status code : $status , body : ${this.bodyAsText()}"),
                    error = null
                )
            }
            else -> {
                throw AuthenticationErrors.BadRequestException(
                    errorMessage = BaseString.DynamicString("status code : $status , body : ${this.bodyAsText()}"),
                    error = null
                )
            }
        }
    }.mapLeft { e ->
        when (e) {
            is UnresolvedAddressException -> ConnectionErrors.NoInternetConnection(error = e)
            is AuthenticationErrors.AuthenticationException -> e
            is AuthenticationErrors.BadRequestException -> e
            is ConnectionErrors.ServerErrorException -> e
            else -> UnknownError(error = e)
        }
    }
}
