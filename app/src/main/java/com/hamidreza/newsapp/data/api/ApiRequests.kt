package com.hamidreza.newsapp.data.api

import com.hamidreza.newsapp.conts.Conts.API_KEY
import com.hamidreza.newsapp.data.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country") countryName: String,
        @Query("page") page: Int,
        @Query("category") category: String,
        @Query("apiKey") api: String = API_KEY
    ): Deferred<Response<NewsResponse>>

    @GET("v2/everything")
    fun searchForNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") api: String = API_KEY
    ): Deferred<Response<NewsResponse>>

}