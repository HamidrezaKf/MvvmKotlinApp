package com.hamidreza.newsapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.hamidreza.newsapp.data.api.ApiRequests
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.data.paginate.NewsPagingSource
import javax.inject.Inject

class NewsRepository @Inject constructor(val api:ApiRequests) {

    //fun getBreakingNews(country:String,page:Int,category:String) = api.getBreakingNews(country,page,category)

    fun getBreakingNews(country:String,category:String) = Pager(config =
        PagingConfig(
            pageSize = 5,
            maxSize = 100
        ),
            pagingSourceFactory = {
                NewsPagingSource(api,country,category,isSearch = false)
            }
        ).liveData

    fun searchForNews(query:String) = Pager(
        config = PagingConfig(
            pageSize = 5,
            maxSize = 100
        ),
        pagingSourceFactory = {
            NewsPagingSource(api,query = query,isSearch = true)
        }
    ).liveData

}