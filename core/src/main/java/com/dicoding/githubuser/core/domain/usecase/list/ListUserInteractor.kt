package com.dicoding.githubuser.core.domain.usecase.list

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import javax.inject.Inject

class ListUserInteractor @Inject constructor(private val repository: GitHubUserRepository) :
    ListUserUseCase {
    override fun searchUser(username: String) = repository.searchUser(username)
}