package com.hamidreza.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamidreza.newsapp.data.model.Article

@Database(entities = [Article::class],version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun getDao():ArticleDao

    companion object {
        val DATABASE_NAME = "article_db.db"
    }
}