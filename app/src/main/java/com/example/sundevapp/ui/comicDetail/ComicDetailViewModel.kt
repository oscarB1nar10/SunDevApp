package com.example.sundevapp.ui.comicDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComicDetailViewModel @Inject constructor(
    private var comicDetailRepository: ComicDetailRepository) : ViewModel(){
    //const
    private val TAG = "ComicDetailViewModel"
    //vars
    val comicDetailResponse = comicDetailRepository.comicDetailResponse
    val handlerExceptions =  comicDetailRepository.handlerExceptions

    init {
        Log.i(TAG,"Injection is working...")
    }

    fun getComicDetail(comicDetail:  String){
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                comicDetailRepository.getComicDetail(comicDetail)
            }
            job.join()
        }
    }
}