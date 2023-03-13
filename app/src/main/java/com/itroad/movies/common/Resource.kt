
package com.itroad.movies.app.common
/*
       Generic sealed class used when loading ressources
 */
sealed class Resource<out T> {

    object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T) : Resource<T>()

    data class Failure(val message: AppMessage, val detail: ErrorDetail) : Resource<Nothing>()
}
