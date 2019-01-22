package com.example.artyom.githubclient.data.repository

import com.example.artyom.githubclient.data.remote.RemoteUserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteUserService: RemoteUserService
) : Repository{

    override fun getUsers(userId: Long, perPage: Int) = remoteUserService.requestGetUsers(userId, perPage)
}