package com.dicoding.githubuser.core.domain.usecase.detail

import com.dicoding.githubuser.core.domain.model.DetailUser
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface UserDetailUseCase {
    fun getUserDetail(username: String): Flow<Result<DetailUser>>

    fun getFollow(position: Int, username: String): Flow<Result<List<GitHubUser>>>

    fun isUserFavourite(username: String): Flow<Boolean>

    suspend fun addToFavourite(user: GitHubUser)

    suspend fun removeFromFavourite(user: GitHubUser)
}