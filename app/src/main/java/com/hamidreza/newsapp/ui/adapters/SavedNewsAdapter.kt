package com.hamidreza.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.databinding.FragmentSavedNewsBinding
import com.hamidreza.newsapp.databinding.NewsItemBinding

class SavedNewsAdapter(val listener: onItemClickListener) : RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>() {


    private val differCallBack =object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    inner class SavedNewsViewHolder(val binding:NewsItemBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = differ.currentList[position]
                    item?.let {
                        listener.onClick(it)
                    }
                }
            }
        }

        fun bind(article: Article){
            binding.apply {
                Glide.with(itemView).load(article.url).transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.image_error).into(ivArticleImage)
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvSource.text = article.source.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val currentArticle = differ.currentList[position]
        currentArticle?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}