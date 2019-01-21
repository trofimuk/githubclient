package com.example.artyom.githubclient.di

import android.app.Application


class UserApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .remoteModule(RemoteModule()).build()
    }
}

