package com.example.artyom.githubclient.data.remote

import io.reactivex.Observable
import retrofit2.http.GET

interface RemoteUserService {

    @GET(RemoteContract.USERS)
    fun requestGetUsers(): Observable<MutableList<UserResponse>>
}