package com.marchenaya.marvelcomics.ui.comicList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.marchenaya.marvelcomics.base.fragment.BaseVMFragment
import com.marchenaya.marvelcomics.databinding.FragmentComicListBinding

class ComicListFragment : BaseVMFragment<ComicListFragmentViewModel, FragmentComicListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicListBinding =
        FragmentComicListBinding::inflate

    override val viewModelClass = ComicListFragmentViewModel::class

}