package com.marchenaya.marvelcomics.ui.comicList.networkItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.marchenaya.data.model.Comic
import com.marchenaya.marvelcomics.databinding.ComicListItemBinding
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject

class ComicListFragmentAdapter @Inject constructor() :
    PagingDataAdapter<Comic, ComicListFragmentViewHolder>(ComicItemDiffCallback()) {

    var onItemClickListener: (ComicDataWrapper) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListFragmentViewHolder =
        ComicListFragmentViewHolder(
            ComicListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ComicListFragmentViewHolder, position: Int) {
        val comicItem = getItem(position)
        if (comicItem != null) {
            holder.bind(ComicDataWrapper(comicItem), onItemClickListener)
        }
    }


}
