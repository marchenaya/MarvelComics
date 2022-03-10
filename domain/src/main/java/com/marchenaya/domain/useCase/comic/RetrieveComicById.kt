package com.marchenaya.domain.useCase.comic

import com.marchenaya.domain.model.Comic
import com.marchenaya.domain.repository.ComicRepository
import com.marchenaya.domain.useCase.base.SuspendParametrizedUseCase
import javax.inject.Inject

class RetrieveComicById @Inject constructor(private val comicRepository: ComicRepository) :
    SuspendParametrizedUseCase<Comic, RetrieveComicById.Params>() {

    override suspend fun build(params: Params) =
        comicRepository.getComicById(params.comicId)

    data class Params(val comicId: Int)
}