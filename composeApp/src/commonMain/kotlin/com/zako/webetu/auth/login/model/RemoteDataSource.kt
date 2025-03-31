package com.zako.webetu.auth.login.model

import arrow.core.Either
import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.errors.AppError

interface RemoteDataSource {
    /**
     * login the user  using the api provided by Progress Team
     * @param registrationNumber the registration number of the user
     * @param password the password of the user
     * @return Either<AppError , LoginResponse> the response of the login or the error
     *
     */
    suspend fun login(registrationNumber: String, password: String): Either<AppError, UserAuth>
}
