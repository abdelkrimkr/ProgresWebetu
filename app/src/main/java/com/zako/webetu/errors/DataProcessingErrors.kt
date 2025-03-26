package com.zako.webetu.errors

import com.zako.webetu.R
import com.zako.webetu.utils.BaseString

sealed class DataProcessingErrors(
    override var errorMessage: BaseString?,
    override var error: Throwable?
) : AppError, Throwable() {

    data class DataProcessingException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.data_processing_error),
        override var error: Throwable?
    ) : DataProcessingErrors(errorMessage = errorMessage, error)

    data class DataSerializationException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.data_serialization_error),
        override var error: Throwable?
    ) : DataProcessingErrors(errorMessage = errorMessage, error)
}
