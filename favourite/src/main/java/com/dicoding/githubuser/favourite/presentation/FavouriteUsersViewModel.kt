package com.dicoding.githubuser.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.githubuser.core.domain.usecase.favourite.FavouriteUsersUseCase
import javax.inject.Inject

class FavouriteUsersViewModel @Inject constructor(useCase: FavouriteUsersUseCase) :
    ViewModel() {
    val getFavouriteUsers = useCase.getFavouriteUsers().asLiveData()
}