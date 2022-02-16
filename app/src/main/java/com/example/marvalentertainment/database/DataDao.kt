package com.example.marvalentertainment.database

import androidx.room.*
import com.example.marvalentertainment.model.Result
import kotlinx.coroutines.flow.Flow


@Dao
interface DataDao {
    @Query("SELECT * FROM Result WHERE characterId=characterId")
    fun getCharacterslist(): Flow<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characters: List<Result>)

    @Delete
    suspend fun delete(characterId: Result)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatelist(characters: List<Result>)

    @Query("SELECT * FROM Result where isValid = 1")
    fun getSelected(): Flow<List<Result>>

    @Query("SELECT * FROM Result WHERE isValid ==:subjectFormId")
    fun getSubjectForm(subjectFormId: Boolean): List<Result>


}