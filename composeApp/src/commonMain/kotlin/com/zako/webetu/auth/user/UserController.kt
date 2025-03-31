package com.zako.webetu.auth.user

import arrow.core.Either
import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.auth.user.model.UserRepository
import com.zako.webetu.errors.AppError
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Controller for the user authentication
 * this object is aim to unify the access to the user in one place following the singleton pattern so that it's
 * impossible that can be many instances of the user repository in the app
 *
 * this class will be responsible for the response to the errors from the repo and handel it
 *
 * @author B.zakaria
 */
object UserController : KoinComponent {
    private val userRepository: UserRepository by inject<UserRepository>()


    suspend fun getUserAuth() : Either<AppError , UserAuth>{
       return userRepository.getUserAuth()
    }

    suspend fun saveUserAuth(user: UserAuth) : Either<AppError , Boolean>{
        return userRepository.saveUserAuth(user)
    }


}