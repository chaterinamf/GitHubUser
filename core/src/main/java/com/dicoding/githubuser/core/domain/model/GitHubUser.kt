package com.dicoding.githubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    val username: String,
    val avatarUrl: String
) : Parcelable