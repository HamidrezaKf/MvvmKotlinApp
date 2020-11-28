package com.hamidreza.newsapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hamidreza.newsapp.conts.Conts.BASE_URL
import com.hamidreza.newsapp.data.api.ApiRequests
import com.hamidreza.newsapp.utils.Connectivity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {


    @Provides
    @Singleton
    fun provideGson(): Gson{
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideConnectivity(@ApplicationContext context: Context):Connectivity{
        return Connectivity(context)
    }

    @Provides
    @Singleton
    fun provideHttpClient(connectivity: Connectivity):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(connectivity).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit.Builder):ApiRequests{
        return retrofit.build().create(ApiRequests::class.java)
    }
}