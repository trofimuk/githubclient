package com.example.artyom.githubclient.view

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.artyom.githubclient.data.datasource.UserDataSource
import com.example.artyom.githubclient.data.repository.UserRepository
import com.example.artyom.githubclient.di.UserApplication
import com.example.artyom.githubclient.domain.User
import com.example.artyom.githubclient.data.datasource.UserDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel : ViewModel(), LifecycleObserver{

    @Inject lateinit var userRepository: UserRepository

    private val sourceFactory: UserDataSourceFactory

    var liveDataUserList: LiveData<PagedList<User>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 15

    init {
        initializeDagger()
        sourceFactory = UserDataSourceFactory(userRepository)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        liveDataUserList = LivePagedListBuilder<Long,User>(sourceFactory, config).build()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel(){
        for (disposible in UserDataSource.allCompositeDisposable){
            compositeDisposable.addAll(disposible)
        }
        compositeDisposable.clear()
    }

    override fun onCleared() {
        unSubscribeViewModel()
        super.onCleared()
    }

    private fun initializeDagger() = UserApplication.appComponent.inject(this)
}