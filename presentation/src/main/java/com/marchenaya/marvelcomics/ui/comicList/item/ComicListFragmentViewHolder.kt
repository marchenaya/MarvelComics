package com.marchenaya.marvelcomics.ui.comicList.item

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.databinding.ComicListItemBinding
import com.marchenaya.marvelcomics.extensions.hide
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper

const val IMAGE_WIDTH = 300
const val IMAGE_HEIGHT = 450

class ComicListFragmentViewHolder(private val binding: ComicListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var comicDataWrapper: ComicDataWrapper? = null

    fun bind(comicDataWrapper: ComicDataWrapper?, onItemClickListener: (ComicDataWrapper) -> Unit) {
        if (comicDataWrapper == null) {
            with(binding) {
                comicListItemImage.hide()
                comicListItemTitle.text = itemView.resources.getString(R.string.loading)
            }
        } else {
            showComicData(comicDataWrapper, onItemClickListener)
        }
    }

    private fun showComicData(
        comicDataWrapper: ComicDataWrapper,
        onItemClickListener: (ComicDataWrapper) -> Unit
    ) {
        this.comicDataWrapper = comicDataWrapper
        with(binding) {
            Glide.with(itemView.context)
                .load(comicDataWrapper.getImage())
                .placeholder(R.drawable.ic_image)
                .override(IMAGE_WIDTH, IMAGE_HEIGHT)
                .into(comicListItemImage)
            comicListItemTitle.text = comicDataWrapper.getTitle()
        }
        itemView.setOnClickListener { onItemClickListener(comicDataWrapper) }
    }

}