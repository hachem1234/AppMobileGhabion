
package com.itroad.movies.ma.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.itroad.movies.BuildConfig
import com.itroad.movies.app.common.Constants
import com.itroad.movies.app.data.net.HttpInterceptor
import com.itroad.movies.app.data.net.MoviesService
import com.itroad.movies.app.data.net.customFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.net.ssl.SSLContext

import javax.net.ssl.TrustManager

import javax.net.ssl.X509TrustManager


import java.lang.Exception
import java.security.SecureRandom
import java.security.cert.X509Certificate


/**
 * provides singleton instances of retrofit, okHttp and gson
 */
@Module

@InstallIn

class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: Lazy<OkHttpClient>, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .customFactory { client.get().newCall(it) } // Initialize Okhttp lazily
             //.client(unSafeOkHttpClient().build())
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient {

        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .writeTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .readTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpInterceptor())

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }

        // .addInterceptor(ChuckInterceptor(context))

        return builder.build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideLeenaService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }


    fun unSafeOkHttpClient(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                    /**
                     * Todo
                     */
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                    /**
                     * Todo
                     */
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            if (trustAllCerts.isNotEmpty() && trustAllCerts.first() is X509TrustManager) {
                okHttpClient.sslSocketFactory(
                    sslSocketFactory,
                    trustAllCerts.first() as X509TrustManager
                )
                okHttpClient.hostnameVerifier { _, _ -> true }
            }

            return okHttpClient
        } catch (e: Exception) {
            return okHttpClient
        }
    }
}