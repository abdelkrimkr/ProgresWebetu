package com.zako.webetu.errors

import com.zako.webetu.R
import com.zako.webetu.utils.BaseString

sealed class ConnectionErrors(
    override  var errorMessage : BaseString?,
    override var error: Throwable?
 ) : AppError , Throwable() {


     data class NoInternetConnection(
         override var errorMessage : BaseString? = BaseString.ResString(R.string.no_internet_connection),
         override var error: Throwable?
     ) : ConnectionErrors(errorMessage = errorMessage , error)

        data class TimeoutError(
            override var errorMessage : BaseString? = BaseString.ResString(R.string.timeout_error),
            override var error: Throwable?
        ) : ConnectionErrors(errorMessage = errorMessage , error)

    data class SSLHandshakeError(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.ssl_handshake_error),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage , error)

    data class BadResponseException(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.bad_response_error),
        override var error: Throwable?
    ) : ConnectionErrors(errorMessage = errorMessage , error)

    data class ServerErrorException(
        override var errorMessage : BaseString? = BaseString.ResString(R.string.server_error),
        override var error: Throwable?
    ) : AuthenticationErrors(errorMessage = errorMessage , error)

}

