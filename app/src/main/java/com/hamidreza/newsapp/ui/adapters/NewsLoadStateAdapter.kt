package com.hamidreza.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.databinding.PaginateFooterBinding

class NewsLoadStateAdapter(private val retry:() -> Unit) : LoadStateAdapter<NewsLoadStateAdapter.NewsStateViewHolder>(){

    inner class NewsStateViewHolder(val binding:PaginateFooterBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                tv.isVisible = loadState !is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): NewsStateViewHolder {
        val view = PaginateFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsStateViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

}