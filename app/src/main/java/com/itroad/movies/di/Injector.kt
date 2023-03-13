
package com.itroad.movies.ma.app.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.itroad.movies.WeatherApplication

/**
 * Helper extension properties for fragments and activities
 * to facilitate access to dependencies in the dagger graph.
 */

val Activity.injector
    get() = (application as WeatherApplication).component

val Fragment.injector
    get() = (requireActivity().application as WeatherApplication).component
