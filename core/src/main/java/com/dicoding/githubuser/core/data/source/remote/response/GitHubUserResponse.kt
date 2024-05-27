package com.dicoding.githubuser.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUserResponse(
    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable