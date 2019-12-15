package com.example.sundevapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.sundevapp.util.Constans.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object{

        @Singleton
        @JvmStatic
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideConnectivityManager(application: Application): ConnectivityManager {
            return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

    }

}