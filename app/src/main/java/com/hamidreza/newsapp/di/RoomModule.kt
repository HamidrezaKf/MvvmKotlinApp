package com.hamidreza.newsapp.di

import android.content.Context
import androidx.room.Room
import com.hamidreza.newsapp.data.db.ArticleDao
import com.hamidreza.newsapp.data.db.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): ArticleDataBase{
        return Room.databaseBuilder(
            context,
            ArticleDataBase::class.java,
            ArticleDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(articleDataBase: ArticleDataBase):ArticleDao{
        return articleDataBase.getDao()
    }
}