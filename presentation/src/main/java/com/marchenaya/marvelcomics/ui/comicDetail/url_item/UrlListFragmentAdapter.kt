package com.marchenaya.marvelcomics.ui.comicDetail.url_item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.marchenaya.marvelcomics.databinding.UrlListItemBinding
import javax.inject.Inject

class UrlListFragmentAdapter @Inject constructor() :
    ListAdapter<String, UrlListFragmentViewHolder>(UrlItemDiffCallback()) {

    private val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UrlListFragmentViewHolder =
        UrlListFragmentViewHolder(
            UrlListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: UrlListFragmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}