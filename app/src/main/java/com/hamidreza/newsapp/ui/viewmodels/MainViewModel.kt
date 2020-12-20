package com.hamidreza.newsapp.ui.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamidreza.newsapp.conts.Conts.IS_NIGHT

class MainViewModel @ViewModelInject constructor(@Assisted state:SavedStateHandle) :ViewModel(){

    val isNight = state.getLiveData(IS_NIGHT,false)

    fun setNightMode(mode:Boolean){
        isNight.value = mode
    }

}