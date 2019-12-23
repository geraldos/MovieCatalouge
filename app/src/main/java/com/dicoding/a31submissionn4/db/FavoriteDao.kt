package com.dicoding.a31submissionn4.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.a31submissionn4.model.FavoriteModel

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteModel?): Long

    @Query("SELECT * FROM tbFavoriteList WHERE id = 1")
    fun selectMovieFavorite(): List<FavoriteModel?>?

    @Query("SELECT * FROM tbFavoriteList WHERE id = 2")
    fun selectTvShowFavorite(): List<FavoriteModel?>?

    @Query("SELECT title FROM tbFavoriteList WHERE id = :id_fav")
    fun checkIsFavorit(id_fav: Int): String?

    @Query("DELETE FROM tbFavoriteList WHERE id=:id")
    fun deleteFavorite(id: Int): Int
}