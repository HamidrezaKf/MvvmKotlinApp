package com.hamidreza.newsapp.repositories

import com.hamidreza.newsapp.data.api.ApiRequests
import javax.inject.Inject

class NewsRepository @Inject constructor(val api:ApiRequests) {

    fun getBreakingNews(country:String,page:Int,category:String) = api.getBreakingNews(country,page,category)

}