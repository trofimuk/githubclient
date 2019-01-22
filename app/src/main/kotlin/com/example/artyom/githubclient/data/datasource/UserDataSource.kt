package com.example.artyom.githubclient.data.datasource

import android.arch.paging.ItemKeyedDataSource
import com.example.artyom.githubclient.data.remote.RemoteContract
import com.example.artyom.githubclient.data.remote.UserResponse

import com.example.artyom.githubclient.data.repository.UserRepository
import com.example.artyom.githubclient.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userRepository: UserRepository
) :ItemKeyedDataSource<Long, User>() {

    companion object {
        val allCompositeDisposable: MutableList<Disposable> = arrayListOf()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        val disposable = userRepository.getUsers(RemoteContract.START_PER_PAGE,params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userResponse ->
                callback.onResult(transform(userResponse))
            }
        allCompositeDisposable.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        val disposable = userRepository.getUsers(params.key,params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userResponse ->
                callback.onResult(transform(userResponse))
            }
        allCompositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: User): Long = item.id

    private fun transform(users: MutableList<UserResponse>): MutableList<User>{
        val userList = ArrayList<User>()
        users.forEach{
            userList.add(User(it.id,it.login,it.urlAvatar))
        }
        return userList
    }
}