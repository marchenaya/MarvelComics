package com.marchenaya.marvelcomics.ui.comicList.item

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.databinding.ComicListItemBinding
import com.marchenaya.marvelcomics.extensions.hide
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper

class ComicListFragmentViewHolder(private val binding: ComicListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var comicDataWrapper: ComicDataWrapper? = null

    init {
        with(binding) {
            root.setOnClickListener {
                //TODO: nav detail
            }
        }
    }

    fun bind(comicDataWrapper: ComicDataWrapper?) {
        if (comicDataWrapper == null) {
            with(binding) {
                comicListItemImage.hide()
                comicListItemTitle.text = itemView.resources.getString(R.string.loading)
            }
        } else {
            showComicData(comicDataWrapper)
        }
    }

    private fun showComicData(comicDataWrapper: ComicDataWrapper) {
        this.comicDataWrapper = comicDataWrapper
        with(binding) {
            try {
                Glide.with(itemView.context)
                    .load(comicDataWrapper.getImage())
                    .placeholder(R.drawable.ic_image).override(300, 450)
                    .into(comicListItemImage)
            } catch (exception: GlideException) {
                Log.e("azerty", exception.logRootCauses("azerty").toString())
            }
            comicListItemTitle.text = comicDataWrapper.getTitle()
        }

    }

}