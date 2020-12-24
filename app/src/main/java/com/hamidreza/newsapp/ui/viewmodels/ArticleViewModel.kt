package com.hamidreza.newsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidreza.newsapp.data.model.Article
import com.hamidreza.newsapp.repositories.ArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel @ViewModelInject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {

    fun insertArticle(article: Article) = viewModelScope.launch {
        articleRepository.insertArticle(article)
    }

    val getArticles = articleRepository.getArticles()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        articleRepository.deleteArticle(article)


    }
}