package com.dicoding.githubuser.core.di

import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import com.dicoding.githubuser.core.domain.repository.IGitHubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: GitHubUserRepository): IGitHubUserRepository
}