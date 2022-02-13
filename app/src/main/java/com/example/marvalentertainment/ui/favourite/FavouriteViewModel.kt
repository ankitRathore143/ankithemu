package com.example.marvalentertainment.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvalentertainment.model.FavCharactersModel

class FavouriteViewModel : ViewModel() {

    private val favolist = MutableLiveData<List<FavCharactersModel>>().apply {
//        value = "This is gallery Fragment"
    }
    val listfav: MutableLiveData<List<FavCharactersModel>> = favolist
}