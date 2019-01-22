package com.example.artyom.githubclient.view.view_model

import android.arch.lifecycle.*
import com.example.artyom.githubclient.data.repository.UserRepository
import com.example.artyom.githubclient.di.UserApplication
import com.example.artyom.githubclient.domain.UserDetails
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailsViewModel: ViewModel(), LifecycleObserver {

    @Inject lateinit var userRepository: UserRepository

    private var liveUserData: LiveData<UserDetails>? = null

    private val compositeDisposable = CompositeDisposable()

    init {
        initializeDagger()
    }

    fun getUserDetails(login: String): LiveData<UserDetails>? {
        if (liveUserData == null){
            liveUserData = MutableLiveData()
            liveUserData = userRepository.getUserDetails(login)
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