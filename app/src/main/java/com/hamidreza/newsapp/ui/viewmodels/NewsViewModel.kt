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
import retrofit2.awaitResponse
import java.lang.Exception

class NewsViewModel @ViewModelInject constructor(val repo : NewsRepository) :ViewModel() {
    private  val TAG = "NewsViewModel"
    val breakingNews:MutableLiveData<NewsResponse> = MutableLiveData()

    fun getBreakingNews() {
        viewModelScope.launch {
            try {
                //breakingNews.postValue(repo.getBreakingNews("us",1).await())
                val response = repo.getBreakingNews("us",1).awaitResponse()
                if (response.isSuccessful){
                    breakingNews.postValue(response.body())
                }else{
                    Log.e(TAG, "something wrong in else ${response.message()}", )
                }
            }catch (e:Exception){
                Log.e(TAG, "something wrong in catch ${e.message.toString()}", )
            }
        }
    }
}