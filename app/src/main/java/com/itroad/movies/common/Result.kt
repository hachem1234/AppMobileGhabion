
package com.itroad.movies.app.common

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.itroad.movies.R

/*
    Generic response class for api response call
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(
        val message: AppMessage,
        val detail: ErrorDetail = ErrorDetail()
    ) : Result<Nothing>()
}

// Add more detail if needed
class ErrorDetail(
    var keyErreur: String? = "",
    @SerializedName ("error")
    var messageServer: String? = "",
    var message: String? = "",
    var status: Int = 0
)

data class ErrorModel (
    @SerializedName ("error")
    var error: String? = "",)

sealed class AppMessage {
    data class ResourceMessage(
        @StringRes val resMessage: Int,
        val arguments: List<String> = emptyList()
    ) : AppMessage()

    data class StringMessage(val message: String) : AppMessage()

    override fun toString(): String {
        return when (this) {
            is ResourceMessage -> ""
            is StringMessage -> message
        }
    }
}

fun <T> Result<T>.asResource(): Resource<T> {
    return when (this) {
        is Result.Success -> Resource.Success(data)
        is Result.Error -> Resource.Failure(message, detail)
    }
}

suspend fun <I, O> Result<I>.map(mapSuccess: suspend (I) -> O): Result<O> {
    return when (this) {
        is Result.Success -> Result.Success(mapSuccess(data))
        is Result.Error -> this
    }
}



val TECHNICAL_ISSUE_MESSAGE = AppMessage.ResourceMessage(R.string.common_technical_issue)
