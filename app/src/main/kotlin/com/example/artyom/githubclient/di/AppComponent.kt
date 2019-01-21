package com.example.artyom.githubclient.di

import com.example.artyom.githubclient.view.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RemoteModule::class])
@Singleton interface AppComponent {

    fun inject(userViewModel: UserViewModel)
}