package com.dicoding.githubuser.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.githubuser.core.data.source.local.entity.GitHubUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourite_user")
    fun getFavouriteUsers(): Flow<List<GitHubUserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favourite_user WHERE login = :username)")
    fun isUserFavourite(username: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(user: GitHubUserEntity)

    @Delete
    suspend fun removeFromFavourite(user: GitHubUserEntity)
}