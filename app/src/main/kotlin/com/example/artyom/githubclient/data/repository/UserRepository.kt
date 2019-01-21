package com.example.artyom.githubclient.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.artyom.githubclient.data.remote.RemoteUserDataSource
import com.example.artyom.githubclient.data.remote.UserResponse
import com.example.artyom.githubclient.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource
) : Repository{

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getUsers(): LiveData<MutableList<User>> {
        val mutableLiveData = MutableLiveData<MutableList<User>>()
        val disposable = remoteUserDataSource.requestGetUsersSource()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userResponse ->
                mutableLiveData.value = transform(userResponse)
            }
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun transform(users: MutableList<UserResponse>): MutableList<User>{
        val userList = ArrayList<User>()
        users.forEach{
            userList.add(User(it.id,it.login,it.urlAvatar))
        }
        return userList
    }
}