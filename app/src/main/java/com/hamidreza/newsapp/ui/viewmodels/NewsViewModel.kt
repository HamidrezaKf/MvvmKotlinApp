package com.hamidreza.newsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.hamidreza.newsapp.repositories.NewsRepository


class NewsViewModel @ViewModelInject constructor(
    val repo: NewsRepository,
) : ViewModel() {


    private val TAG = "NewsViewModel"


    companion object {
        private const val DEFAULT_CATEGORY = "general"
    }


    private val currentSearch: MutableLiveData<String> = MutableLiveData()

    private val currentCategory: MutableLiveData<String> = MutableLiveData(DEFAULT_CATEGORY)


    val news = currentCategory.switchMap {
        repo.getBreakingNews("us", it).cachedIn(viewModelScope)
    }

    val searchNews = currentSearch.switchMap {
        repo.searchForNews(it).cachedIn(viewModelScope)
    }

    fun setCategory(category: String) {
        currentCategory.value = category
    }

    fun setSearch(query: String) {
        currentSearch.value = query
    }


}

