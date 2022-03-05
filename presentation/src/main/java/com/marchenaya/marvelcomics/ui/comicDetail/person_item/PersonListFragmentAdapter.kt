package com.marchenaya.marvelcomics.ui.comicDetail.person_item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.marchenaya.marvelcomics.databinding.PersonListItemBinding
import com.marchenaya.marvelcomics.wrapper.PersonDataWrapper
import javax.inject.Inject

class PersonListFragmentAdapter @Inject constructor() :
    ListAdapter<PersonDataWrapper, PersonListFragmentViewHolder>(PersonItemDiffCallback()) {

    private val items: MutableList<PersonDataWrapper> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonListFragmentViewHolder =
        PersonListFragmentViewHolder(
            PersonListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonListFragmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<PersonDataWrapper>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}