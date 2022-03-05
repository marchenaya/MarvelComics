package com.marchenaya.marvelcomics.wrapper

import com.marchenaya.data.model.Comic

class ComicDataWrapper(private val comic: Comic) {

    fun getId() = comic.id

    fun getTitle() = comic.title

    fun getImage() = comic.image

    fun getDescription() = comic.description

    fun getCharacters() = comic.characters.map { CharacterDataWrapper(it) }

    fun getPageCount() = comic.pageCount

    fun getUrls() = comic.urls

    fun getCreators() = comic.creators.map { CreatorDataWrapper(it) }

}