package com.marchenaya.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class Comic(
    @PrimaryKey val id: Int,
    val prevKey: Int?,
    val nextKey: Int?,
    val title: String,
    val description: String,
    val pageCount: Int,
    val image: String
) {

    @Ignore
    var urls = emptyList<String>()

    @Ignore
    var characters = emptyList<Character>()

    @Ignore
    var creators = emptyList<Creator>()

    constructor(
        id: Int,
        prevKey: Int?,
        nextKey: Int?,
        title: String,
        description: String,
        pageCount: Int,
        image: String,
        urls: List<String>,
        characters: List<Character>,
        creators: List<Creator>
    ) : this(
        id, prevKey, nextKey, title, description, pageCount, image
    ) {
        this.urls = urls
        this.characters = characters
        this.creators = creators
    }

}
