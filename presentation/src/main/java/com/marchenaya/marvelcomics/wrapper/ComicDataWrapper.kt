package com.marchenaya.marvelcomics.wrapper

import com.marchenaya.data.model.Comic

class ComicDataWrapper(private val comic: Comic) {

    fun getTitle() = comic.title

    fun getImage() = comic.image

}