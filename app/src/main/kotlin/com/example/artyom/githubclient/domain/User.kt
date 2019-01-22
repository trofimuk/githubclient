package com.example.artyom.githubclient.domain
import java.io.Serializable

data class User(val id: Long, val login: String, val urlAvatar: String): Serializable