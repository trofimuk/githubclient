package com.example.artyom.githubclient.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteUserService {

    @GET(RemoteContract.USERS)
    fun requestGetUsers(@Query(RemoteContract.SINCE) userId: Long, @Query(RemoteContract.PER_PAGE) perPage: Int): Observable<MutableList<UserResponse>>
}