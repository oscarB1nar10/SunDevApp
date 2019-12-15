package com.example.sundevapp.util.comicResponse

data class Result(
    val api_detail_url: String,
    val date_last_updated: String,
    val description: String,
    val id: Int,
    val image: Image,
    val name: String
)