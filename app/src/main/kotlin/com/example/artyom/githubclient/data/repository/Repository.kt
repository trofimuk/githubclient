package com.example.artyom.githubclient.data.repository

import com.example.artyom.githubclient.data.remote.UserResponse
import io.reactivex.Observable

interface Repository {

    fun getUsers(userId: Long, perPage: Int): Observable<MutableList<UserResponse>>
}