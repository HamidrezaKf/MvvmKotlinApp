package com.hamidreza.newsapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hamidreza.newsapp.conts.Conts.SHARED_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModules {

    @Singleton
    @Provides
    fun provideSharedPrefrence(@ApplicationContext context:Context):SharedPreferences{
        return context.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE)
    }
}