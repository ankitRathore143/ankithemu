package com.example.marvalentertainment.model

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.marvalentertainment.retrofit.ApiHelper
import kotlinx.coroutines.*

//TODO Ankit Rathore 46143234

class MainViewModel constructor(
    private val mainRepository: ApiHelper
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    val movieList = MutableLiveData<List<Result>>()

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()


    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getMovieData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.data?.results)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}