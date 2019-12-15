package com.example.sundevapp.network

import com.example.sundevapp.util.comicDetailResponse.ComicDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicDetailAPI {

    @GET("issue/{comicDetail}/")
    fun getComicDetail(
        @Path("comicDetail") comicDetail: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String) : Call<ComicDetailResponse>

}