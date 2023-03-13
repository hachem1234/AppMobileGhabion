
package com.itroad.movies

import android.app.Application
import com.itroad.movies.app.di.AppComponent
import com.itroad.movies.app.di.DaggerAppComponent

import dagger.hilt.android.HiltAndroidApp

/**
 * Base class for maintaining global application state.
 * This app is using Hilt and must contain an Application class annotated with @HiltAndroidApp.
 * @HiltAndroidApp kicks off the code generation of the Hilt components and also generates a base class for the application that uses those generated components.
 */

@HiltAndroidApp
class WeatherApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this


    }

    companion object {
        lateinit var INSTANCE: WeatherApplication
            private set
    }

    /**
     *  function to get Firebase Analytics instance
     */

}
