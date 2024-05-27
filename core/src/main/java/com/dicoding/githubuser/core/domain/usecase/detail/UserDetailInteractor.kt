package com.dicoding.githubuser.core.domain.usecase.detail

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import com.dicoding.githubuser.core.domain.model.GitHubUser
import javax.inject.Inject

class UserDetailInteractor @Inject constructor(private val repository: GitHubUserRepository) :
    UserDetailUseCase {
    override fun getUserDetail(username: String) = repository.getUserDetail(username)

    override fun getFollow(position: Int, username: String) =
        repository.getFollow(position, username)

    override fun isUserFavourite(username: String) = repository.isUserFavourite(username)

    override suspend fun addToFavourite(user: GitHubUser) = repository.addToFavourite(user)

    override suspend fun removeFromFavourite(user: GitHubUser) =
        repository.removeFromFavourite(user)
}