package com.example.ceibaapp.di.main

import androidx.lifecycle.ViewModel
import com.example.sundevapp.di.ViewModelKey
import com.example.sundevapp.ui.comicBook.ComicBookViewModel
import com.example.sundevapp.ui.comicDetail.ComicDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule{


    @Binds
    @IntoMap
    @ViewModelKey(ComicBookViewModel::class)
    abstract fun bindComicBookFragmentViewModel(viewModel: ComicBookViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ComicDetailViewModel::class)
    abstract fun bindComicDetailFragmentViewModel(viewModel: ComicDetailViewModel): ViewModel



}