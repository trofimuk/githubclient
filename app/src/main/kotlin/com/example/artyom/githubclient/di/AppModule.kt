package com.example.artyom.githubclient.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val userApplication: UserApplication) {

    @Provides @Singleton fun provideContext(): Context = userApplication

}