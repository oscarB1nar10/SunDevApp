package com.example.sundevapp.util.comicDetailResponse

import com.example.sundevapp.util.comicResponse.Image

data class Results(
    val character_credits: List<Characters>,
    val team_credits: List<Team>,
    val location_credits: List<Location>,
    val concept_credits: List<Concepts>,
    val image: Image
)