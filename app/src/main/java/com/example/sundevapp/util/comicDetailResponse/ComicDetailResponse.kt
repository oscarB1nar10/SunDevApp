package com.example.sundevapp.util.comicDetailResponse

data class ComicDetailResponse(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: Results,
    val status_code: Int,
    val version: String
    )