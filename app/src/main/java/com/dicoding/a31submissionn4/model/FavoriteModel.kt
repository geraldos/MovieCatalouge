package com.dicoding.a31submissionn4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbFavoriteList")
class FavoriteModel : Serializable {
    @PrimaryKey
    var id = 0
    @ColumnInfo(name = "poster")
    var poster: String? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @ColumnInfo(name = "release_date")
    var release_date: String? = null
    @ColumnInfo(name = "vote_average")
    var vote_average: String? = null
    @ColumnInfo(name = "overview")
    var overview: String? = null

}