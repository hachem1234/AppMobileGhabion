
package com.itroad.movies.app.di
import android.content.Context
import com.itroad.movies.ma.app.di.AppModule
import com.itroad.movies.ui.movies.MoviesViewModel

import dagger.BindsInstance
import dagger.Component
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn
@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
/**
 * Factory is a type with a single method that returns a new component instance each time it is called.
 * The parameters of that method allow the caller to provide the modules, dependencies and bound instances required by the component
 */
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
    val weatherViewModel: MoviesViewModel

}
