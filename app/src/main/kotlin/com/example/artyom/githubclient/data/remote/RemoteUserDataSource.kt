package com.example.artyom.githubclient.data.remote

import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(private val remoteUserService: RemoteUserService){

    fun requestGetUsers() =
            remoteUserService.requestGetUsers()
}