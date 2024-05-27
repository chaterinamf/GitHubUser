package com.dicoding.githubuser.core.data.source.remote.network

import com.dicoding.githubuser.core.data.source.remote.response.DetailUserResponse
import com.dicoding.githubuser.core.data.source.remote.response.GitHubUserResponse
import com.dicoding.githubuser.core.data.source.remote.response.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUser(@Query("q", encoded = true) queryParam: String): SearchUserResponse

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): DetailUserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<GitHubUserResponse>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<GitHubUserResponse>
}