package com.hamidreza.newsapp.data.api

import com.hamidreza.newsapp.conts.Conts.API_KEY
import com.hamidreza.newsapp.data.model.remote.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("v2/top-headlines")
     fun getBreakingNews(
        @Query("country") countryName:String,
        @Query("page") page:Int,
        @Query("apiKey") api:String = API_KEY
    ) : Deferred<NewsResponse>



}