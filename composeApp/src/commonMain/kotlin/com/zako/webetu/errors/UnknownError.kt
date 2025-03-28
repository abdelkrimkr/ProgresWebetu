package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.unknown_error

data class UnknownError(
    override var errorMessage: BaseString? = BaseString.ResString(Res.string.unknown_error),
    override var error: Throwable?
) : AppError
