package com.example.marvalentertainment.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvalentertainment.retrofit.ApiHelper

class MyViewModelFactory constructor(
    private val apiHelper: ApiHelper,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.apiHelper) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}