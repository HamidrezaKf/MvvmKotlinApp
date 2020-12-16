package com.hamidreza.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.databinding.NewsItemBinding

class NewsPagingAdapter : PagingDataAdapter<Article,NewsPagingAdapter.NewsArticleViewHolder>(diffCallback = newsComparator) {


companion object{
     val newsComparator = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}


    inner class NewsArticleViewHolder(val binding:NewsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Article){
            binding.apply {

                Glide.with(itemView).load(item.urlToImage).transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.image_error).into(ivArticleImage)

                tvTitle.text = item.title
                tvDescription.text = item.description
                tvSource.text = item.source.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }



}