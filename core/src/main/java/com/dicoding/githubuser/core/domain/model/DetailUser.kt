package com.dicoding.githubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    val username: String,
    val id: Int,
    val email: String? = null,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String
) : Parcelable
