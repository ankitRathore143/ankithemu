package com.example.marvalentertainment.ui.favourite

import androidx.lifecycle.*
import com.example.marvalentertainment.model.Result
import com.example.marvalentertainment.repository.DataRepository
import kotlinx.coroutines.launch

class FavouriteViewModel constructor(private val repository: DataRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val selectWords: LiveData<List<Result>> = repository.alldata.asLiveData()

    val getdatagav: LiveData<List<Result>> = repository.getFavouriteCharacters().asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: List<Result>) = viewModelScope.launch {
        repository.insert(word)
    }

    fun update(result: List<Result>) = viewModelScope.launch {
        repository.updatelist(result)
    }

    fun removeFavoChara(result: Result) = viewModelScope.launch {
        repository.removeCharacterFromFavourite(result)
    }

    fun getFavouriteCharacters() =
        repository.getFavouriteCharacters()


}

class FavViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouriteViewModel(this.repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
