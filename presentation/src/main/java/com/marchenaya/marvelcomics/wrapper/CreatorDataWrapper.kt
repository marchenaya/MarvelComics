package com.marchenaya.marvelcomics.wrapper

import com.marchenaya.domain.model.Creator


class CreatorDataWrapper(private val creator: Creator) : PersonDataWrapper {

    override fun getId() = creator.id

    override fun getName() = creator.name

    override fun getImage() = creator.image

}
