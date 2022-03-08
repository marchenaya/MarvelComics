package com.marchenaya.marvelcomics.ui.comicList.loadItem

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.marchenaya.marvelcomics.databinding.ComicLoadStateItemBinding

class ComicLoadStateViewHolder(
    private val binding: ComicLoadStateItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.comicRetryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.comicLoadErrorMsg.text = loadState.error.localizedMessage
        }
        binding.comicLoadProgressBar.isVisible = loadState is LoadState.Loading
        binding.comicRetryButton.isVisible = loadState is LoadState.Error
        binding.comicLoadErrorMsg.isVisible = loadState is LoadState.Error
    }

}