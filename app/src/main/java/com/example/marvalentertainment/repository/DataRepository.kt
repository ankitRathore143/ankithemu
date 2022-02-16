package com.example.marvalentertainment.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.marvalentertainment.BuildConfig
import com.example.marvalentertainment.database.DataDao
import com.example.marvalentertainment.model.Result
import kotlinx.coroutines.flow.Flow


class DataRepository(private val dataDao: DataDao) {


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val alldata: Flow<List<Result>> = dataDao.getSelected()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(result: List<Result>) {
        dataDao.insert(result)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updatelist(result: List<Result>) {
        dataDao.updatelist(result)
    }

    @WorkerThread
    suspend fun removeCharacterFromFavourite(characterId: Result) =
        dataDao.delete(characterId)

    fun getFavouriteCharacters() =
        dataDao.getCharacterslist()

}