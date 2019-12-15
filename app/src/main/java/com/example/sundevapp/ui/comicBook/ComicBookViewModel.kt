package com.example.sundevapp.ui.comicBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComicBookViewModel @Inject constructor(
    private var comicBookRepository: ComicBookRepository) : ViewModel(){

    //const
    private val TAG = "ComicBookViewModel"
    //vars
    val comicResponse = comicBookRepository.comicResponse
    val handlerExceptions =  comicBookRepository.handlerExceptions

    init {
        Log.i(TAG,"Injection is working...")
    }

    fun getComics(){
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                comicBookRepository.getComics()
            }
            job.join()
        }
    }
}