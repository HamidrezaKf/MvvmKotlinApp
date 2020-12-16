package com.hamidreza.newsapp.data.paginate

import android.util.Log
import androidx.paging.PagingSource
import com.hamidreza.newsapp.conts.Conts.START_PAGE_INDEX
import com.hamidreza.newsapp.data.api.ApiRequests
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.data.model.remote.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class NewsPagingSource(
    private val api: ApiRequests,
    private val countryName: String?=null,
    private val category: String?=null,
    private val query:String?=null,
    private val isSearch:Boolean


    ) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: START_PAGE_INDEX
        val response : Deferred<Response<NewsResponse>>
        return try {
            if (isSearch){
                response = api.searchForNews(query!!,position)
            }else{
                response = api.getBreakingNews(countryName!!, position, category!!)
            }
            val newsResponse = response.await()
            if (newsResponse.isSuccessful) {
                val articles = newsResponse.body()?.articles!!
                 LoadResult.Page(
                    data = articles,
                    prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                    nextKey = if (articles.isEmpty()) null else position + 1
                )
            }else{
                LoadResult.Error(HttpException(newsResponse))
            }
        } catch (e: IOException) {
             LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }

}