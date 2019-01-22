package com.example.artyom.githubclient.data.repository

import android.arch.lifecycle.LiveData

import com.example.artyom.githubclient.data.remote.UserResponse
import com.example.artyom.githubclient.domain.UserDetails
import io.reactivex.Observable

interface Repository {

    fun getUsers(userId: Long, perPage: Int): Observable<MutableList<UserResponse>>
    fun getUserDetails(login: String): LiveData<UserDetails>

}