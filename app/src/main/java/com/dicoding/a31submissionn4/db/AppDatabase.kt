package com.dicoding.a31submissionn4.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.a31submissionn4.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao?
}