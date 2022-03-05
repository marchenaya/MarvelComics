package com.marchenaya.marvelcomics.ui.comicDetail.url_item

import androidx.recyclerview.widget.DiffUtil

class UrlItemDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean =
        oldItem == newItem

}