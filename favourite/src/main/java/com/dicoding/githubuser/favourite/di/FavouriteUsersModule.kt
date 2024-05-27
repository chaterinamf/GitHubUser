package com.dicoding.githubuser.favourite.di

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersInteractor
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FavouriteUsersModule {
    @Provides
    fun provideFavouriteUsersUseCase(repository: GitHubUserRepository): FavouriteUsersUseCase {
        return FavouriteUsersInteractor(repository)
    }
}