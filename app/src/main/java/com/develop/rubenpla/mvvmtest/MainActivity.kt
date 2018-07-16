package com.develop.rubenpla.mvvmtest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.develop.rubenpla.mvvmtest.databinding.ActivityMainBinding
import com.develop.rubenpla.mvvmtest.ui.post.PostListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private lateinit var  viewModel : PostListViewModel

    private var errorSnackBar : Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         *  Setting binding layout & LayoutManager for RecyclerView component
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        /**
         * Initializing viewModel and setting to binding ViewModel attribute
         */
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)

        /**
         * Handling potential errors
         */
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) {
            showError(errorMessage)
        } else {
            hideError()
        }
        })


        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage : Int) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }
}
