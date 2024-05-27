package com.dicoding.githubuser.core.data.repository

import com.dicoding.githubuser.core.data.source.local.room.FavouriteDao
import com.dicoding.githubuser.core.data.source.remote.network.ApiService
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.core.domain.repository.IGitHubUserRepository
import com.dicoding.githubuser.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubUserRepository @Inject constructor(
    private val apiService: ApiService,
    private val favouriteDao: FavouriteDao
) : IGitHubUserRepository {
    override fun searchUser(username: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.searchUser(username)
            val listUser = DataMapper.mapResponsesToDomain(response.items)
            emit(Result.Success(listUser))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUserDetail(username: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(username)
            val detail = DataMapper.mapDetailResponseToDomain(response)
            emit(Result.Success(detail))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFollow(position: Int, username: String) = flow {
        emit(Result.Loading)
        try {
            val response = if (position == 0) apiService.getFollowers(username)
            else apiService.getFollowing(username)
            val listUser = DataMapper.mapResponsesToDomain(response)
            emit(Result.Success(listUser))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavouriteUsers() = favouriteDao.getFavouriteUsers().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun isUserFavourite(username: String) =
        favouriteDao.isUserFavourite(username)

    override suspend fun addToFavourite(user: GitHubUser) =
        favouriteDao.addToFavourite(DataMapper.mapDomainToEntity(user))

    override suspend fun removeFromFavourite(user: GitHubUser) =
        favouriteDao.removeFromFavourite(DataMapper.mapDomainToEntity(user))
}