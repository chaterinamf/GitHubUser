package com.dicoding.githubuser.di

import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DaggerDependencies {
    fun provideFavouriteUsersUseCase(): FavouriteUsersUseCase
}