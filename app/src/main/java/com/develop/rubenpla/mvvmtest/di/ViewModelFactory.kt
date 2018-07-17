package com.develop.rubenpla.mvvmtest.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.develop.rubenpla.mvvmtest.model.database.AppDataBase
import com.develop.rubenpla.mvvmtest.ui.post.PostListViewModel

class ViewModelFactory(val activity: AppCompatActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if  (modelClass.isAssignableFrom(PostListViewModel::class.java)) {

            val db = Room.databaseBuilder(activity.applicationContext,
                    AppDataBase::class.java,
                    "posts").build()
            @Suppress("UNCHECKED_CAST")

           return PostListViewModel(db.PostDao()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}