package com.dicoding.githubuser.favourite.utils

import android.content.Context
import com.dicoding.githubuser.di.DaggerDependencies
import com.dicoding.githubuser.favourite.di.DaggerFavouriteUsersComponent
import com.dicoding.githubuser.favourite.presentation.FavouriteUsersFragment
import dagger.hilt.android.EntryPointAccessors

internal fun FavouriteUsersFragment.inject(context: Context) {
    DaggerFavouriteUsersComponent
        .builder()
        .context(context)
        .appDependencies(
            EntryPointAccessors.fromApplication(
                context, DaggerDependencies::class.java
            )
        )
        .build()
        .inject(this)
}