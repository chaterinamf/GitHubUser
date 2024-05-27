package com.dicoding.githubuser.favourite.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersUseCase
import com.dicoding.githubuser.favourite.presentation.FavouriteUsersViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val favouriteUsersUseCase: FavouriteUsersUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavouriteUsersViewModel::class.java) ->
                FavouriteUsersViewModel(favouriteUsersUseCase) as T

            else -> throw Throwable("Unknown view model class: " + modelClass.name)
        }
    }
}