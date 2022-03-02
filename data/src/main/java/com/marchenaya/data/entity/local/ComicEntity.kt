package com.marchenaya.data.entity.local

data class ComicEntity(
    val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val image: String,
    val urls: List<String>,
    val characters: List<CharacterEntity>,
    val creators: List<CreatorEntity>
)