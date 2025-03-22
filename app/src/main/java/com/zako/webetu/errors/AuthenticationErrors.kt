package com.zako.webetu.errors

import com.zako.webetu.R
import com.zako.webetu.utils.BaseString

sealed class AuthenticationErrors(
    override var errorMessage : BaseString?,
    override var error: Throwable?
) : AppError , Throwable() {

    data class BadResponseException(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.bad_response_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage , error)

    data class BadRequestException(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.bad_request_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage , error)

    data class AuthenticationException(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.authentication_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage , error)

}