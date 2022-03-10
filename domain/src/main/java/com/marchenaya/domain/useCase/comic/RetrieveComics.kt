package com.marchenaya.domain.useCase.comic

import androidx.paging.PagingData
import com.marchenaya.domain.model.Comic
import com.marchenaya.domain.repository.ComicRepository
import com.marchenaya.domain.useCase.base.FlowParametrizedUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RetrieveComics @Inject constructor(private val comicRepository: ComicRepository) :
    FlowParametrizedUseCase<Flow<PagingData<Comic>>, RetrieveComics.Params>() {

    override fun build(params: Params) =
        comicRepository.getComicList(params.query, params.filterByFavorite)

    data class Params(val query: String, val filterByFavorite: Boolean)
}