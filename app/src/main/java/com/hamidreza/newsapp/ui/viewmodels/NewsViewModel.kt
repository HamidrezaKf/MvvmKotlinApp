package com.hamidreza.newsapp.ui.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidreza.newsapp.data.model.remote.NewsResponse
import com.hamidreza.newsapp.repositories.NewsRepository
import com.hamidreza.newsapp.utils.NoInternetExceptions
import com.hamidreza.newsapp.utils.ResultWrapper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception

class NewsViewModel @ViewModelInject constructor(val repo : NewsRepository) :ViewModel() {
    private val TAG = "NewsViewModel"
    val breakingNews: MutableLiveData<ResultWrapper<NewsResponse>> = MutableLiveData()

    fun getBreakingNews() {
        viewModelScope.launch {
            try {
                //breakingNews.postValue(repo.getBreakingNews("us",1).await())
                breakingNews.postValue(ResultWrapper.Loading())
                val response = repo.getBreakingNews("us", 1).await()
                breakingNews.postValue(handleResponse(response))
            } catch (e: NoInternetExceptions) {
                breakingNews.postValue(ResultWrapper.Error(e.message.toString()))
            }catch (e: Exception){
                breakingNews.postValue(ResultWrapper.Error(e.message.toString()))
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
    }
}