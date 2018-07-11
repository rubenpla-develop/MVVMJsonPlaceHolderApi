package com.develop.rubenpla.mvvmtest.di.component

import com.develop.rubenpla.mvvmtest.di.module.NetworkModule
import com.develop.rubenpla.mvvmtest.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for view models.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface  ViewModelInjector {

    /**
     * Injects required dependencies into the specified PostListViewModel
     * @param postListViewModel PostListViewModel in wich to inject the dependencies
     */
    fun inject(postListViewModel : PostListViewModel)

    @Component.Builder
    interface  Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModel : NetworkModule) : Builder
    }
}