package com.example.artyom.githubclient.data.repository

import android.arch.lifecycle.LiveData
import com.example.artyom.githubclient.domain.User

interface Repository {
    fun getUsers(): LiveData<MutableList<User>>
}