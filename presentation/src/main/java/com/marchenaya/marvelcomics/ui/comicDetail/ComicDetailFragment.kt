package com.marchenaya.marvelcomics.ui.comicDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marchenaya.data.extension.observeSafe
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.fragment.BaseVMFragment
import com.marchenaya.marvelcomics.databinding.FragmentComicDetailBinding
import com.marchenaya.marvelcomics.extensions.hide
import com.marchenaya.marvelcomics.extensions.show
import com.marchenaya.marvelcomics.ui.comicDetail.person_item.PersonListFragmentAdapter
import com.marchenaya.marvelcomics.ui.comicDetail.url_item.UrlListFragmentAdapter
import javax.inject.Inject

const val IMAGE_WIDTH = 300
const val IMAGE_HEIGHT = 450

class ComicDetailFragment :
    BaseVMFragment<ComicDetailFragmentViewModel, FragmentComicDetailBinding>() {

    private lateinit var comicDetailFragmentArgs: ComicDetailFragmentArgs

    @Inject
    lateinit var urlListFragmentAdapter: UrlListFragmentAdapter

    @Inject
    lateinit var charactersListFragmentAdapter: PersonListFragmentAdapter

    @Inject
    lateinit var creatorsListFragmentAdapter: PersonListFragmentAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding =
        FragmentComicDetailBinding::inflate

    override val viewModelClass = ComicDetailFragmentViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComicDetailFragmentArgs()
    }

    private fun getComicDetailFragmentArgs() {
        val args: ComicDetailFragmentArgs by navArgs()
        comicDetailFragmentArgs = args
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startOfLoading()
        setupRecyclerViews()
        retrieveComicDetail()
    }


    private fun startOfLoading() {
        binding {
            comicDetailTitleText.hide()
            comicDetailDescriptionText.hide()
            comicDetailPageCountText.hide()
            comicDetailUrlsText.hide()
            comicDetailCharactersText.hide()
            comicDetailCreatorsText.hide()
        }
    }

    private fun setupRecyclerViews() {
        binding {
            comicDetailUrlsRecyclerView.apply {
                layoutManager = object : LinearLayoutManager(requireContext()) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                adapter = urlListFragmentAdapter
            }
            val charactersLayoutManager = LinearLayoutManager(requireContext())
            charactersLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            comicDetailCharactersRecyclerView.apply {
                layoutManager = charactersLayoutManager
                adapter = charactersListFragmentAdapter
            }
            val creatorsLayoutManager = LinearLayoutManager(requireContext())
            creatorsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            comicDetailCreatorsRecyclerView.apply {
                layoutManager = creatorsLayoutManager
                adapter = creatorsListFragmentAdapter
            }
        }
    }

    private fun retrieveComicDetail() {
        viewModel.retrieveComicDetail(comicDetailFragmentArgs.comicId)
        viewModel.getComicLiveData().observeSafe(viewLifecycleOwner) {
            binding {
                Glide.with(requireContext())
                    .load(it.getImage())
                    .placeholder(R.drawable.ic_image)
                    .override(IMAGE_WIDTH, IMAGE_HEIGHT)
                    .into(comicDetailImage)
                comicDetailTitle.text = it.getTitle()
                if (it.getDescription().isEmpty()) {
                    comicDetailDescription.hide()
                } else {
                    comicDetailDescriptionText.show()
                    comicDetailDescription.text = it.getDescription()
                }
                comicDetailPageCount.text = it.getPageCount().toString()

                if (it.getUrls().isEmpty()) {
                    comicDetailUrlsRecyclerView.hide()
                } else {
                    comicDetailUrlsText.show()
                    urlListFragmentAdapter.setItems(it.getUrls())
                }
                if (it.getCharacters().isEmpty()) {
                    comicDetailCharactersRecyclerView.hide()
                } else {
                    comicDetailCharactersText.show()
                    charactersListFragmentAdapter.setItems(it.getCharacters())
                }
                if (it.getCreators().isEmpty()) {
                    comicDetailCreatorsRecyclerView.hide()
                } else {
                    comicDetailCreatorsText.show()
                    creatorsListFragmentAdapter.setItems(it.getCreators())
                }
            }
            endOfLoading()

        }
    }

    private fun endOfLoading() {
        binding {
            comicDetailTitleText.show()
            comicDetailPageCountText.show()
            comicDetailProgressBar.hide()
        }
    }

}