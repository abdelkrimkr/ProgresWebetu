package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.authentication_error
import webetu.composeapp.generated.resources.bad_request_error
import webetu.composeapp.generated.resources.bad_response_error
import webetu.composeapp.generated.resources.no_default_user

sealed class AuthenticationErrors(
    override var errorMessage: BaseString?,
    override var error: Throwable?
) : AppError, Throwable() {

    data class BadResponseException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.bad_response_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage, error)

    data class BadRequestException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.bad_request_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage, error)

    data class AuthenticationException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.authentication_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage, error)

    // no default user
    data class NoDefaultUserException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.no_default_user),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage, error)
}
