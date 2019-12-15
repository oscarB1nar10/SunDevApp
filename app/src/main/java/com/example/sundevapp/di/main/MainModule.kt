package com.example.ceibaapp.di.main

import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerCharactersAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerConceptsAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerLocationsAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerTeamsAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule{

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRecyclerCharactersAdapter(): RecyclerCharactersAdapter {
            return RecyclerCharactersAdapter()}

        @JvmStatic
        @Provides
        fun provideRecyclerTeamsAdapter(): RecyclerTeamsAdapter {
            return RecyclerTeamsAdapter()}

        @JvmStatic
        @Provides
        fun provideRecyclerLocationsAdapter(): RecyclerLocationsAdapter {
            return RecyclerLocationsAdapter()}

        @JvmStatic
        @Provides
        fun provideRecyclerConceptsAdapter(): RecyclerConceptsAdapter {
            return RecyclerConceptsAdapter()}






    }

}