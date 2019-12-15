package com.example.sundevapp.di

import com.example.ceibaapp.di.main.MainFragmentBuildersModule
import com.example.ceibaapp.di.main.MainModule
import com.example.ceibaapp.di.main.MainScope
import com.example.ceibaapp.di.main.MainViewModelsModule
import com.example.sundevapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMaintActivity(): MainActivity

}