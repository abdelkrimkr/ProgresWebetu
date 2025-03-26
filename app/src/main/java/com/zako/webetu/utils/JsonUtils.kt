package com.zako.webetu.utils

import arrow.core.Either
import com.zako.webetu.errors.AppError
import com.zako.webetu.errors.DataProcessingErrors
import kotlinx.serialization.json.Json

inline fun <reified T> String.parseJson(): Either<AppError, T> {
    return Either.catch {
        val serializer = Json { ignoreUnknownKeys = true }
        serializer.decodeFromString<T>(this)
    }.mapLeft { e ->
        DataProcessingErrors.DataProcessingException(error = e)
    }
}
