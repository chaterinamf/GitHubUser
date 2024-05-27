package com.dicoding.githubuser.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserResponse(

    @SerializedName("login")
    val username: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("followers")
    val followers: Int? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("following")
    val following: Int? = null,

    @SerializedName("name")
    val name: String? = null
) : Parcelable