package com.example.artyom.githubclient.view

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.example.artyom.githubclient.di.UserApplication

class UserViewModel : ViewModel(), LifecycleObserver{

    init {
        initializeDagger()
    }

    private fun initializeDagger() = UserApplication.appComponent.inject(this)
}