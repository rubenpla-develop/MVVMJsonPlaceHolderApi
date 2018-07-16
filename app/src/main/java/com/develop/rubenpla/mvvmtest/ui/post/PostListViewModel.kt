package com.develop.rubenpla.mvvmtest.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.develop.rubenpla.mvvmtest.R
import com.develop.rubenpla.mvvmtest.base.BaseViewModel
import com.develop.rubenpla.mvvmtest.model.Post
import com.develop.rubenpla.mvvmtest.network.PostApi
import com.develop.rubenpla.mvvmtest.ui.post.adapter.PostListAdapter
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

    val postListAdapter : PostListAdapter = PostListAdapter()

    val loadingVisibility : MutableLiveData<Int> = MutableLiveData()
    val errorMessage : MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish()}
                .subscribe( { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError()})
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(result: List<Post>) {
        postListAdapter.updatePostListData(result)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    private fun getLoadingVisibility() : Int? {
        return loadingVisibility.value
    }

}