package com.hamidreza.newsapp.repositories

import com.hamidreza.newsapp.data.db.ArticleDao
import com.hamidreza.newsapp.data.model.Article
import javax.inject.Inject

class ArticleRepository @Inject constructor(val db:ArticleDao) {

    suspend fun insertArticle(article: Article) = db.insertArticle(article)

    fun getArticles() = db.getArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)
}