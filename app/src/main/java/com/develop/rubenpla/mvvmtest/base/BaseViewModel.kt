package com.develop.rubenpla.mvvmtest.base

import android.arch.lifecycle.ViewModel
import com.develop.rubenpla.mvvmtest.di.component.DaggerViewModelInjector
import com.develop.rubenpla.mvvmtest.di.component.ViewModelInjector
import com.develop.rubenpla.mvvmtest.di.module.NetworkModule
import com.develop.rubenpla.mvvmtest.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel() {
    /**
     * Injecting required dependencies in BaseVIewModel
     */
    private val injector : ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects required the dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}