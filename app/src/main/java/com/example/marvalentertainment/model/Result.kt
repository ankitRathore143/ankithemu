package com.example.marvalentertainment.model

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Result", indices = [Index(value = ["name"], unique = true)])
data class Result(


    @PrimaryKey(autoGenerate = true)
    val characterId: Int,
    @SerializedName("id") var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("isValid") var isValid: Boolean? = null,

    @SerializedName("thumbnail")
    @Embedded
    var thumbnail: Thumbnail? = Thumbnail(),

    ) {
    override fun toString(): String {
        return "Results(id=$id, name=$name, description=$description,modified=$modified,resourceURI=$resourceURI,isValid=$isValid)"
    }
}