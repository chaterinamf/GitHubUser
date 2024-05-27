package com.dicoding.githubuser.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.usecase.detail.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val useCase: UserDetailUseCase) :
    ViewModel() {
    fun getUserDetail(username: String) = useCase.getUserDetail(username).asLiveData()

    fun isUserFavourite(username: String) = useCase.isUserFavourite(username).asLiveData()

    fun addToFavourite(user: GitHubUser) = viewModelScope.launch {
        useCase.addToFavourite(user)
    }

    fun removeFromFavourite(user: GitHubUser) = viewModelScope.launch {
        useCase.removeFromFavourite(user)
    }
}