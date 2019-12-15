package com.example.sundevapp.util.comicResponse

data class Result(
    val aliases: Any,
    val api_detail_url: String,
    val cover_date: String,
    val date_added: String,
    val date_last_updated: String,
    val deck: Any,
    val description: String,
    val has_staff_review: Boolean,
    val id: Int,
    val image: Image,
    val issue_number: String,
    val name: String,
    val site_detail_url: String,
    val store_date: Any,
    val volume: Volume
)