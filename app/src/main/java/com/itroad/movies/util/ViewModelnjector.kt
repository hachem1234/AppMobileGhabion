package com.itroad.movies.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels

inline fun <reified T : ViewModel> Fragment.viewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}

inline fun <reified T : ViewModel> Fragment.activityViewModel(
    crossinline provider: () -> T
) = activityViewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}

inline fun <reified T : ViewModel> Fragment.flowViewModel(
    @IdRes navGraphId: Int,
    crossinline provider: () -> T
) = navGraphViewModels<T>(navGraphId) {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}
