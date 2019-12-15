package com.example.sundevapp.ui.comicBook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sundevapp.network.ComicAPI
import com.example.sundevapp.util.Constans
import com.example.sundevapp.util.HandlerException
import com.example.sundevapp.util.NetworkState
import com.example.sundevapp.util.comicResponse.ComicResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ComicBookRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val networkState: NetworkState){

    //const
    private val TAG = "ComicBookRepository"
    //vars
    var comicResponse: MutableLiveData<ComicResponse> = MutableLiveData()
    var handlerExceptions : MutableLiveData<HandlerException> = MutableLiveData()

    suspend fun getComics(){
        val call = retrofit.create(ComicAPI::class.java).getComics(Constans.API_KEY,"json")
        if(networkState.getNetworkState()) {
            withContext(Dispatchers.IO) {
                withTimeout(5000L) {
                    call.enqueue(object : Callback<ComicResponse> {

                        override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                            if (response.code() == 200) {
                                response.let {
                                    Log.i(TAG,"onResponse: $it")
                                    comicResponse.value = it.body()
                                    handlerExceptions.value = null

                                }

                            }
                        }

                        override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                            Log.e(TAG, "An error was happen: ${t.message}")
                            val handlerException = t.message?.let { HandlerException(it) }
                            handlerExceptions.value = handlerException
                        }
                    })
                }
            }
        }else{
            Log.i(TAG,"Without internet: ")
            val handlerException = HandlerException("No internet connection")
            handlerExceptions.value = handlerException
        }
    }

}