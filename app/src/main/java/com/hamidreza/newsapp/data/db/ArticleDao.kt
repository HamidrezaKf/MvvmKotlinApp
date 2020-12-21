package com.hamidreza.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamidreza.newsapp.data.model.remote.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}