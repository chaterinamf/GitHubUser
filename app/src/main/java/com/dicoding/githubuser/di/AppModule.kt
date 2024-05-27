package com.dicoding.githubuser.di

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import com.dicoding.githubuser.core.domain.usecase.detail.UserDetailInteractor
import com.dicoding.githubuser.core.domain.usecase.detail.UserDetailUseCase
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersInteractor
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersUseCase
import com.dicoding.githubuser.core.domain.usecase.list.ListUserInteractor
import com.dicoding.githubuser.core.domain.usecase.list.ListUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideListUserUseCase(repository: GitHubUserRepository): ListUserUseCase =
        ListUserInteractor(repository)

    @Singleton
    @Provides
    fun provideUserDetailUseCase(repository: GitHubUserRepository): UserDetailUseCase =
        UserDetailInteractor(repository)

    @Singleton
    @Provides
    fun provideFavouriteUsersUseCase(repository: GitHubUserRepository): FavouriteUsersUseCase =
        FavouriteUsersInteractor(repository)
}