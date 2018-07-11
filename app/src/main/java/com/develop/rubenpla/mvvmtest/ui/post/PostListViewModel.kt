package com.develop.rubenpla.mvvmtest.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.develop.rubenpla.mvvmtest.base.BaseViewModel
import com.develop.rubenpla.mvvmtest.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel : BaseViewModel() {

    /**
     *  We'll inject an instance of PostApi in order to get result from Api
     */

    @Inject
    lateinit var postApi : PostApi

    private lateinit var subscription : Disposable

    val loadingVisibility : MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish()}
                .subscribe( { onRetrievePostListSuccess()},
                        { onRetrievePostListError()})
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }

    private fun getLoadingVisibility() : Int? {
        return loadingVisibility.value
    }

}