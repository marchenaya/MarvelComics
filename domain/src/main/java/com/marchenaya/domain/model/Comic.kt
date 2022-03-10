package com.marchenaya.domain.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val image: String,
    val urls: List<String>,
    val characters: List<Character>,
    val creators: List<Creator>,
    val favorite: Boolean
)
