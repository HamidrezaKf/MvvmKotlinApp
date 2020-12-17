package com.hamidreza.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamidreza.newsapp.data.model.remote.Article

@Database(entities = [Article::class],version = 1)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun getDao():ArticleDao

    companion object {
        val DATABASE_NAME = "article_db.db"
    }
}