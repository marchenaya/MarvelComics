package com.marchenaya.marvelcomics.ui.comicDetail.url_item

import androidx.recyclerview.widget.RecyclerView
import com.marchenaya.marvelcomics.databinding.UrlListItemBinding

class UrlListFragmentViewHolder(private val binding: UrlListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String) {
        with(binding) {
            urlListItemText.text = url
        }
    }

}