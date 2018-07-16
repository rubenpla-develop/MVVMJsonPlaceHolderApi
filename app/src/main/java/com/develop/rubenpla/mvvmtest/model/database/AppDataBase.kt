package com.develop.rubenpla.mvvmtest.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.develop.rubenpla.mvvmtest.model.Post
import com.develop.rubenpla.mvvmtest.model.dao.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun PostDao() : PostDao
}