package com.example.sundevapp.ui.comicDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sundevapp.network.ComicAPI
import com.example.sundevapp.network.ComicDetailAPI
import com.example.sundevapp.util.Constans
import com.example.sundevapp.util.HandlerException
import com.example.sundevapp.util.NetworkState
import com.example.sundevapp.util.comicDetailResponse.ComicDetailResponse
import com.example.sundevapp.util.comicResponse.ComicResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ComicDetailRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val networkState: NetworkState){

    //const
    private val TAG = "ComicDetalRepository"
    //vars
    var comicDetailResponse: MutableLiveData<ComicDetailResponse> = MutableLiveData()
    var handlerExceptions : MutableLiveData<HandlerException> = MutableLiveData()

    suspend fun getComicDetail(comicDetail: String){
        val comicDetailString = getComicDetailString(comicDetail)
        val call = retrofit.create(ComicDetailAPI::class.java).getComicDetail(comicDetailString, Constans.API_KEY,"json")
        if(networkState.getNetworkState()) {
            withContext(Dispatchers.IO) {
                withTimeout(5000L) {
                    call.enqueue(object : Callback<ComicDetailResponse> {

                        override fun onResponse(call: Call<ComicDetailResponse>, response: Response<ComicDetailResponse>) {
                            if (response.code() == 200) {
                                response.let {
                                    Log.i(TAG,"onResponse: $it")
                                    comicDetailResponse.value = it.body()
                                    handlerExceptions.value = null
                                }

                            }
                        }

                        override fun onFailure(call: Call<ComicDetailResponse>, t: Throwable) {
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

    private fun getComicDetailString(comicDetail: String): String{
        val index = comicDetail.indexOf("issue")
        val lastAppearanceOfSlash = comicDetail.lastIndexOf("/")
        return comicDetail.substring(index+6, lastAppearanceOfSlash)
    }
}