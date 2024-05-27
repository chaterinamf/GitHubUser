package com.dicoding.githubuser.presentation.detail.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.githubuser.core.data.repository.GitHubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(private val repository: GitHubUserRepository) : ViewModel() {
    fun getFollow(position: Int, username: String) = repository.getFollow(position, username).asLiveData()
}