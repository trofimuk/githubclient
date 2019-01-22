package com.example.artyom.githubclient.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.artyom.githubclient.data.repository.UserRepository
import com.example.artyom.githubclient.domain.User
import javax.inject.Inject

class UserDataSourceFactory @Inject constructor(
    private val userRepository: UserRepository
) : DataSource.Factory<Long, User>() {
    val usersDataSourceLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Long, User> {
        val userDataSource = UserDataSource(userRepository)
        usersDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}