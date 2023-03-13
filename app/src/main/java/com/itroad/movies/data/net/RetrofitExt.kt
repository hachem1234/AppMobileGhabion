
package com.itroad.movies.app.data.net

import okhttp3.Call
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit

fun <T> Response<T?>.isSuccess(): Boolean {
    val body = body()
    if (isSuccessful && body != null) {
        return true
    }
    return false
}

fun <T> Response<T?>.nonNullBody(): T {
    val body = body()
    if (isSuccess()) {
        return body!!
    }
    throw IllegalAccessException("Body null, check isSuccess() first")
}

inline fun Retrofit.Builder.customFactory(
    crossinline body: (Request) -> Call
) = callFactory(object : Call.Factory {
    override fun newCall(request: Request): Call = body(request)
})
