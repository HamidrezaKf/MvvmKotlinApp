package com.hamidreza.newsapp.ui.adapters

import com.hamidreza.newsapp.data.model.remote.Article

interface onItemClickListener {

    fun onClick(item: Article)

}