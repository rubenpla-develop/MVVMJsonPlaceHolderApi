package com.develop.rubenpla.mvvmtest.ui.post.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.develop.rubenpla.mvvmtest.R
import com.develop.rubenpla.mvvmtest.databinding.ItemPostBinding
import com.develop.rubenpla.mvvmtest.model.Post
import com.develop.rubenpla.mvvmtest.ui.post.PostViewModel

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private lateinit var postList : List<Post>


    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): PostViewHolder {
        val binding : ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_post, parent, false)

        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(:: postList.isInitialized) postList.size else 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun updatePostListData(postList : List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    class PostViewHolder(private val binding : ItemPostBinding) :
            RecyclerView.ViewHolder(binding.root) {

        private val viewModel : PostViewModel = PostViewModel()

        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel

        }
    }
}