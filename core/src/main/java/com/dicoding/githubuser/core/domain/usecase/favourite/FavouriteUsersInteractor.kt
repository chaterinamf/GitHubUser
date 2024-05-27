package com.dicoding.githubuser.core.domain.usecase.favourite

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import javax.inject.Inject

class FavouriteUsersInteractor @Inject constructor(private val repository: GitHubUserRepository) :
    FavouriteUsersUseCase {
    override fun getFavouriteUsers() = repository.getFavouriteUsers()
}