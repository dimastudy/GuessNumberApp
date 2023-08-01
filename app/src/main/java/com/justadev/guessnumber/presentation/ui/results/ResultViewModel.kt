package com.justadev.guessnumber.presentation.ui.results

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ResultViewModel(
    private val result: String
) : ViewModel() {

    private val resultLiveData = MutableLiveData<String>()

    init {
        resultLiveData.value = result
    }

    fun observeResult(lifecycleOwner: LifecycleOwner, observer: Observer<String>){
        resultLiveData.observe(lifecycleOwner, observer)
    }

    class Factory(val result: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ResultViewModel::class.java))
                return ResultViewModel(result) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}