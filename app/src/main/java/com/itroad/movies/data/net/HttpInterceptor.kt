package com.itroad.movies.app.data.net


import com.itroad.movies.app.common.BaseViewModel
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor : Interceptor, BaseViewModel() {


    override fun intercept(chain: Interceptor.Chain): Response {

        synchronized(this) {
            var originalRequest = chain.request()
            var authenticationRequest = request(originalRequest)
            var initialResponse = chain.proceed(authenticationRequest)

            return initialResponse
        }
    }



    private fun request(originalRequest: Request): Request {
        return originalRequest.newBuilder().build()
    }





}

