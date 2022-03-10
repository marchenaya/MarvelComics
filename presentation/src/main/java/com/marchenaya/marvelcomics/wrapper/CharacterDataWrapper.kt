package com.marchenaya.marvelcomics.wrapper

import com.marchenaya.domain.model.Character


class CharacterDataWrapper(private val character: Character) : PersonDataWrapper {

    override fun getId() = character.id

    override fun getName() = character.name

    override fun getImage() = character.image

}
