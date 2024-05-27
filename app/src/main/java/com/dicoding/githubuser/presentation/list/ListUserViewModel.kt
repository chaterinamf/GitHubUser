package com.dicoding.githubuser.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.core.domain.usecase.list.ListUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(private val useCase: ListUserUseCase) :
    ViewModel() {
    private val _listUser = MutableLiveData<Result<List<GitHubUser>>>()
    val listUser: LiveData<Result<List<GitHubUser>>> = _listUser

    fun searchUser(username: String) = viewModelScope.launch {
        useCase.searchUser(username).collect {
            _listUser.value = it
        }
    }
}