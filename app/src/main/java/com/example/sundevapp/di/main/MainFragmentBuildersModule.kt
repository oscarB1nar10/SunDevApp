package com.example.ceibaapp.di.main


import com.example.sundevapp.ui.comicBook.ComicBook
import com.example.sundevapp.ui.comicBook.ComicBookRepository
import com.example.sundevapp.ui.comicDetail.ComicDetail
import com.example.sundevapp.ui.comicDetail.ComicDetailRepository
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeComicBookFragment(): ComicBook

    @ContributesAndroidInjector
    abstract fun contributeComicDetailFragment(): ComicDetail

    @ContributesAndroidInjector
    abstract fun contributeComicBookRepository() : ComicBookRepository

    @ContributesAndroidInjector
    abstract fun contributeComicDetailRepository() : ComicDetailRepository






}