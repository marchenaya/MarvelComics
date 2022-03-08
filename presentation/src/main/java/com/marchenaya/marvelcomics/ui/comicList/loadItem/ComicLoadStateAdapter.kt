package com.marchenaya.marvelcomics.ui.comicList.loadItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.marchenaya.marvelcomics.databinding.ComicLoadStateItemBinding

class ComicLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ComicLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ComicLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ComicLoadStateViewHolder = ComicLoadStateViewHolder(
        ComicLoadStateItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), retry
    )
}