package com.example.artyom.githubclient.domain
import java.io.Serializable

data class UserDetails(
    val urlAvatar: String?,
    val name: String?,
    val email: String?,
    val company: String?,
    val countFollowing: String?,
    val countFollowers: String?,
    val dateCreatedAccount: String?
): Serializable