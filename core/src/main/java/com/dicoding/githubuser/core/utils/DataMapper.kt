package com.dicoding.githubuser.core.utils

import com.dicoding.githubuser.core.data.source.local.entity.GitHubUserEntity
import com.dicoding.githubuser.core.data.source.remote.response.DetailUserResponse
import com.dicoding.githubuser.core.data.source.remote.response.GitHubUserResponse
import com.dicoding.githubuser.core.domain.model.DetailUser
import com.dicoding.githubuser.core.domain.model.GitHubUser

object DataMapper {
    fun mapResponsesToDomain(input: List<GitHubUserResponse>): List<GitHubUser> = input.map {
        GitHubUser(it.username, it.avatarUrl)
    }

    fun mapEntitiesToDomain(input: List<GitHubUserEntity>): List<GitHubUser> = input.map {
        GitHubUser(it.username, it.avatarUrl)
    }

    fun mapDomainToEntity(input: GitHubUser): GitHubUserEntity =
        GitHubUserEntity(input.username, input.avatarUrl)

    fun mapDetailResponseToDomain(input: DetailUserResponse) = DetailUser(
        input.username,
        input.id,
        input.email,
        input.followers ?: 0,
        input.avatarUrl,
        input.following ?: 0,
        input.name.orEmpty(),
    )
}