package com.zako.webetu.errors

import com.zako.webetu.R
import com.zako.webetu.utils.BaseString

sealed class DatabaseErrors(
    override var errorMessage: BaseString? = BaseString.ResString(R.string.database_error),
    override var error: Throwable?
) : AppError {

    data class DatabaseErrorException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseEmptyException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_empty_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseInsertException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_insert_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseUpdateException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_update_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseDeleteException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_delete_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseQueryException(
        override var errorMessage: BaseString? = BaseString.ResString(R.string.database_query_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)
}
