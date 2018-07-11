package com.develop.rubenpla.mvvmtest

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.develop.rubenpla.mvvmtest.databinding.ActivityMainBinding
import com.develop.rubenpla.mvvmtest.ui.post.PostListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private lateinit var  viewModel : PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         *  Setting binding layout & LayoutManager for RecyvlerView component
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        /**
         * initializing viewModel and setting to binding viewmodel attribute
         */
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
    }


}
