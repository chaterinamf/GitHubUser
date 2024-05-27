package com.dicoding.githubuser.core.domain.usecase.list

import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ListUserUseCase {
    fun searchUser(username: String): Flow<Result<List<GitHubUser>>>
}