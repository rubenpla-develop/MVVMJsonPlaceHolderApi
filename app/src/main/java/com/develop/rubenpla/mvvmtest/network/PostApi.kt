package com.develop.rubenpla.mvvmtest.network

import com.develop.rubenpla.mvvmtest.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts() : Observable<List<Post>>

}