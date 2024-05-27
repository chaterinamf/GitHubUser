package com.dicoding.githubuser.core.domain.usecase.favourite

import com.dicoding.githubuser.core.domain.model.GitHubUser
import kotlinx.coroutines.flow.Flow

interface FavouriteUsersUseCase {
    fun getFavouriteUsers(): Flow<List<GitHubUser>>
}