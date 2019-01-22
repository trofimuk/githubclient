package com.example.artyom.githubclient.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.artyom.githubclient.data.remote.RemoteUserService
import com.example.artyom.githubclient.data.remote.UserDetailsResponse
import com.example.artyom.githubclient.data.remote.UserResponse
import com.example.artyom.githubclient.domain.User
import com.example.artyom.githubclient.domain.UserDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteUserService: RemoteUserService
) : Repository{

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getUsers(userId: Long, perPage: Int) = remoteUserService.requestGetUsers(userId, perPage)


    override fun getUserDetails(login: String): LiveData<UserDetails> {
        val mutableLiveData = MutableLiveData<UserDetails>()

        val disposable = remoteUserService.requestGetUserDetails(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userDetailsResponse ->
                mutableLiveData.value = transform(userDetailsResponse)
            }

        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun transform(userDetails: UserDetailsResponse): UserDetails{
        return UserDetails(
            userDetails.urlAvatar,
            userDetails.name,
            userDetails.email,
            userDetails.company,
            userDetails.countFollowing,
            userDetails.countFollowers,
            userDetails.dateCreatedAccount
        )
    }
}