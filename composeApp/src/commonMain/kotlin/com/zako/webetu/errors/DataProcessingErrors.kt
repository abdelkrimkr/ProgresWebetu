package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.data_processing_error
import webetu.composeapp.generated.resources.data_serialization_error

sealed class DataProcessingErrors(
    override var errorMessage: BaseString?,
    override var error: Throwable?
) : AppError, Throwable() {

    data class DataProcessingException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.data_processing_error),
        override var error: Throwable?
    ) : DataProcessingErrors(errorMessage = errorMessage, error)

    data class DataSerializationException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.data_serialization_error),
        override var error: Throwable?
    ) : DataProcessingErrors(errorMessage = errorMessage, error)
}
