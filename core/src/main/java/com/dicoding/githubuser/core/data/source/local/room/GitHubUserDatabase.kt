package com.dicoding.githubuser.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.githubuser.core.data.source.local.entity.GitHubUserEntity

@Database(entities = [GitHubUserEntity::class], version = 1, exportSchema = false)
abstract class GitHubUserDatabase : RoomDatabase() {
    abstract fun favouriteUserDao(): FavouriteDao

    companion object {
        const val DATABASE_NAME = "GitHubUser.db"
    }
}