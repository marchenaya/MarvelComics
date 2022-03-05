package com.marchenaya.marvelcomics.ui.comicList.item

import androidx.recyclerview.widget.DiffUtil
import com.marchenaya.data.model.Comic

class ComicItemDiffCallback : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean =
        oldItem.id == newItem.id && oldItem.title == oldItem.title &&
                oldItem.image == newItem.image && oldItem.description == newItem.description
                && oldItem.pageCount == newItem.pageCount && oldItem.urls == newItem.urls
                && oldItem.characters == newItem.characters && oldItem.creators == newItem.creators

}