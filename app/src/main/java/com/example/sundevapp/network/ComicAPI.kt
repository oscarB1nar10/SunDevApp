package com.example.sundevapp.network

import com.example.sundevapp.util.comicResponse.ComicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicAPI {

    @GET("issues/")
    fun getComics(
        @Query("api_key") api_key: String,
        @Query("format") format: String) : Call<ComicResponse>

}