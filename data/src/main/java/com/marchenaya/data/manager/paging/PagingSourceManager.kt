package com.marchenaya.data.manager.paging

import androidx.paging.PagingSource
import com.marchenaya.domain.model.Comic

interface PagingSourceManager {

    fun getPagingSource(
        query: String,
        filterByFavorite: Boolean
    ): PagingSource<Int, Comic>

}