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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GitHubUserDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("github".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context, GitHubUserDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideFavouriteDao(db: GitHubUserDatabase): FavouriteDao = db.favouriteUserDao()
}