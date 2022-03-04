package com.marchenaya.marvelcomics.ui.comicList.item

import androidx.recyclerview.widget.DiffUtil
import com.marchenaya.data.model.Comic

class ComicItemDiffCallback : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean =
        oldItem == newItem
}