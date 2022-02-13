package com.example.marvalentertainment.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Result")
data class Result(
    @PrimaryKey
    val id: Int,
    val comics: Comics,
    val description: String,
    val events: Events,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>,
    val isValid: Boolean
)