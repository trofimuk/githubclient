package com.example.artyom.githubclient.view

import android.arch.lifecycle.*
import com.example.artyom.githubclient.data.repository.UserRepository
import com.example.artyom.githubclient.di.UserApplication
import com.example.artyom.githubclient.domain.User
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel : ViewModel(), LifecycleObserver{

    @Inject lateinit var userRepository: UserRepository

    private val compositeDisposable = CompositeDisposable()
    private var liveUserData: LiveData<MutableList<User>>? = null

    init {
        initializeDagger()
    }

    fun getUserList(): LiveData<MutableList<User>>? {
        if (liveUserData == null){
            liveUserData = MutableLiveData()
            liveUserData = userRepository.getUsers()
        }
        return liveUserData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel(){
        for (disposible in userRepository.allCompositeDisposable){
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