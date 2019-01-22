package com.example.artyom.githubclient.data.remote

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse (
    @SerializedName(RemoteContract.URL_AVATAR) val urlAvatar: String? = null,
    @SerializedName(RemoteContract.NAME) val name: String? = null,
    @SerializedName(RemoteContract.EMAIL) val email: String? = null,
    @SerializedName(RemoteContract.COMPANY) val company: String? = null,
    @SerializedName(RemoteContract.FOLLOWING) val countFollowing: String? = null,
    @SerializedName(RemoteContract.FOLLOWERS)  val countFollowers: String? = null,
    @SerializedName(RemoteContract.CREATED_AT) val dateCreatedAccount: String? = null
)