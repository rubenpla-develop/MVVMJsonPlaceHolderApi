package com.develop.rubenpla.mvvmtest.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.develop.rubenpla.mvvmtest.model.Post

@Dao
interface PostDao {

    @get:Query("SELECT * FROM post")
    val getAllPosts : List<Post>

    @Insert
    fun insertAll(vararg posts : Post)
}