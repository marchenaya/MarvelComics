package com.marchenaya.marvelcomics.ui.comicDetail.person_item

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.consts.IMAGE_HEIGHT
import com.marchenaya.marvelcomics.consts.IMAGE_WIDTH
import com.marchenaya.marvelcomics.databinding.PersonListItemBinding
import com.marchenaya.marvelcomics.wrapper.PersonDataWrapper


class PersonListFragmentViewHolder(private val binding: PersonListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(personDataWrapper: PersonDataWrapper) {
        with(binding) {
            Glide.with(itemView.context)
                .load(personDataWrapper.getImage())
                .placeholder(R.drawable.ic_image)
                .override(IMAGE_WIDTH, IMAGE_HEIGHT)
                .into(personListItemImage)
            personListItemText.text = personDataWrapper.getName()
        }
    }

}