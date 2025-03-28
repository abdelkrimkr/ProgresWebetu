package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.bad_response_error
import webetu.composeapp.generated.resources.no_internet_connection
import webetu.composeapp.generated.resources.server_error
import webetu.composeapp.generated.resources.ssl_handshake_error
import webetu.composeapp.generated.resources.timeout_error

sealed class ConnectionErrors(
    override var errorMessage: BaseString?,
    override var error: Throwable?
) : AppError, Throwable() {

    data class NoInternetConnection(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.no_internet_connection),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage, error)

    data class TimeoutError(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.timeout_error),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage, error)

    data class SSLHandshakeError(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.ssl_handshake_error),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage, error)

    data class BadResponseException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.bad_response_error),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage, error)

    data class ServerErrorException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.server_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage, error)
}
