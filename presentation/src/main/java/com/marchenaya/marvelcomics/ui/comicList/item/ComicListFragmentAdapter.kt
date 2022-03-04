package com.marchenaya.marvelcomics.ui.comicList.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.marchenaya.data.model.Comic
import com.marchenaya.marvelcomics.databinding.ComicListItemBinding
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper

class ComicListFragmentAdapter :
    PagingDataAdapter<Comic, ComicListFragmentViewHolder>(ComicItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListFragmentViewHolder =
        ComicListFragmentViewHolder(
            ComicListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ComicListFragmentViewHolder, position: Int) {
        val comicItem = getItem(position)
        if (comicItem != null) {
            holder.bind(ComicDataWrapper(comicItem))
        }
    }


}
