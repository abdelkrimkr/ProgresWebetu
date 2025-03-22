package com.zako.webetu.errors

import com.zako.webetu.R
import com.zako.webetu.utils.BaseString

data class UnknownError(
    override var errorMessage : BaseString? = BaseString.ResString(R.string.unknown_error),
    override var error: Throwable?
) : AppError
