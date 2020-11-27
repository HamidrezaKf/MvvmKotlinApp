package com.hamidreza.newsapp.ui.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidreza.newsapp.data.model.remote.NewsResponse
import com.hamidreza.newsapp.repositories.NewsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel @ViewModelInject constructor(val repo : NewsRepository) :ViewModel() {
    private  val TAG = "NewsViewModel"
    val breakingNews:MutableLiveData<NewsResponse> = MutableLiveData()

    fun getBreakingNews() {
        viewModelScope.launch {
            try {
                breakingNews.postValue(repo.getBreakingNews("us",1).await())
            }catch (e:Exception){
                Log.e(TAG, "something wrong ${e.message.toString()}", )
            }
        }
    }
}