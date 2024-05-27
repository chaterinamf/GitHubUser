package com.dicoding.githubuser.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.githubuser.core.data.source.local.room.FavouriteDao
import com.dicoding.githubuser.core.data.source.local.room.GitHubUserDatabase
import com.dicoding.githubuser.core.data.source.local.room.GitHubUserDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GitHubUserDatabase =
        Room.databaseBuilder(
            context, GitHubUserDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFavouriteDao(db: GitHubUserDatabase): FavouriteDao = db.favouriteUserDao()
}