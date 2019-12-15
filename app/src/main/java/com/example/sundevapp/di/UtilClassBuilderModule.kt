package com.example.sundevapp.di

import com.example.sundevapp.util.NetworkState
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UtilClassBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeNetworkState(): NetworkState

}