package com.marchenaya.domain.useCase.comic

import com.marchenaya.domain.model.Comic
import com.marchenaya.domain.repository.ComicRepository
import com.marchenaya.domain.useCase.base.SuspendParametrizedUseCase
import javax.inject.Inject

class RemoveComic @Inject constructor(private val comicRepository: ComicRepository) :
    SuspendParametrizedUseCase<Unit, RemoveComic.Params>() {

    override suspend fun build(params: Params) =
        comicRepository.removeComic(params.comic)

    data class Params(val comic: Comic)

}