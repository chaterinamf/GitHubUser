package com.dicoding.githubuser.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_user")
data class GitHubUserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("login")
    val username: String,

    @ColumnInfo("avatar_url")
    val avatarUrl: String
)