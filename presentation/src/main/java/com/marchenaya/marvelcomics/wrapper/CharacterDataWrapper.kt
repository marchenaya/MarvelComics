package com.marchenaya.marvelcomics.wrapper

import com.marchenaya.data.model.Character


class CharacterDataWrapper(private val character: Character) : PersonDataWrapper {

    override fun getId() = character.id

    override fun getName() = character.name

    override fun getImage() = character.image

}
