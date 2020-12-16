package com.hamidreza.newsapp.ui.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.hamidreza.newsapp.data.model.remote.NewsResponse
import com.hamidreza.newsapp.repositories.NewsRepository
import com.hamidreza.newsapp.utils.NoInternetExceptions
import com.hamidreza.newsapp.utils.ResultWrapper
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception


class NewsViewModel @ViewModelInject constructor(val repo : NewsRepository) :ViewModel() {
    private val TAG = "NewsViewModel"

    companion object{
      private  const val DEFAULT_CATEGORY="general"
    }

    var row_index_view_model :MutableLiveData<Int> = MutableLiveData(-1)

    private val currentSearch : MutableLiveData<String> = MutableLiveData()

    private val currentCategory:MutableLiveData<String> = MutableLiveData(DEFAULT_CATEGORY)

    val news = currentCategory.switchMap {
        repo.getBreakingNews("us",it).cachedIn(viewModelScope)
    }

    val searchNews = currentSearch.switchMap {
        repo.searchForNews(it).cachedIn(viewModelScope)
    }

    fun setCategory(category:String){
        currentCategory.value = category
    }

    fun setSearch(query:String){
        currentSearch.value = query
    }



}




/*
    private val _breakingNews: MutableLiveData<ResultWrapper<NewsResponse>> = MutableLiveData()
    val breakingNews:LiveData<ResultWrapper<NewsResponse>> = _breakingNews*/




/*
    fun getBreakingNews(country:String,page:Int,category:String) {
        Log.i(TAG, "ViewModelNews: ")
        viewModelScope.launch {
            try {
                //breakingNews.postValue(repo.getBreakingNews("us",1).await())
                _breakingNews.postValue(ResultWrapper.Loading())
                val response = repo.getBreakingNews(country, page,category).await()
                _breakingNews.postValue(handleResponse(response))
            } catch (e: NoInternetExceptions) {
                _breakingNews.postValue(ResultWrapper.Error(e.message.toString()))
            }catch (e: Exception){
                _breakingNews.postValue(ResultWrapper.Error(e.message.toString()))
            }
        }
    }

    fun handleResponse(response: Response<NewsResponse>): ResultWrapper<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ResultWrapper.Success(it)
            }
        }
        return ResultWrapper.Error(response.message())
    }*/