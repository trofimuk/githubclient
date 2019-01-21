package com.example.artyom.githubclient.data.remote

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(RemoteContract.SUCCESS) val isSuccess: Boolean,
    @SerializedName(RemoteContract.ID) val id: String,
    @SerializedName(RemoteContract.LOGIN) val login: String,
    @SerializedName(RemoteContract.URL_AVATAR) val urlAvatar: String
)