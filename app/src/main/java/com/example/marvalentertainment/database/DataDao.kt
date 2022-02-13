package com.example.marvalentertainment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marvalentertainment.model.Result
import kotlinx.coroutines.flow.Flow


@Dao
interface DataDao {
    @Query("SELECT * FROM Result")
    fun getCharacterslist(): Flow<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characters: Result)

    @Query("DELETE FROM Result")
    suspend fun deleteAll()


//    @Query("SELECT * FROM characters_table where isValid = 1")
//    fun getSelected(): LiveData<List<Result?>?>?


}