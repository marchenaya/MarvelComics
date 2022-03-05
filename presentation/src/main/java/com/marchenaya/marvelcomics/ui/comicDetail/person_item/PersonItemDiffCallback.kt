package com.marchenaya.marvelcomics.ui.comicDetail.person_item

import androidx.recyclerview.widget.DiffUtil
import com.marchenaya.marvelcomics.wrapper.PersonDataWrapper

class PersonItemDiffCallback : DiffUtil.ItemCallback<PersonDataWrapper>() {

    override fun areItemsTheSame(
        oldItem: PersonDataWrapper,
        newItem: PersonDataWrapper
    ): Boolean =
        oldItem.getId() == newItem.getId()

    override fun areContentsTheSame(
        oldItem: PersonDataWrapper,
        newItem: PersonDataWrapper
    ): Boolean =
        oldItem.getId() == newItem.getId() && oldItem.getName() == newItem.getName() && oldItem.getImage() == newItem.getImage()

}