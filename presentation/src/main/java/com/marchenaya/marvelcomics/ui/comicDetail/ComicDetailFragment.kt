package com.marchenaya.marvelcomics.ui.comicDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.fragment.BaseVMFragment
import com.marchenaya.marvelcomics.component.snackbar.SnackbarComponent
import com.marchenaya.marvelcomics.consts.IMAGE_HEIGHT
import com.marchenaya.marvelcomics.consts.IMAGE_WIDTH
import com.marchenaya.marvelcomics.databinding.FragmentComicDetailBinding
import com.marchenaya.marvelcomics.extensions.hide
import com.marchenaya.marvelcomics.extensions.observeSafe
import com.marchenaya.marvelcomics.extensions.show
import com.marchenaya.marvelcomics.ui.comicDetail.person_item.PersonListFragmentAdapter
import com.marchenaya.marvelcomics.ui.comicDetail.url_item.UrlListFragmentAdapter
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject

class ComicDetailFragment :
    BaseVMFragment<ComicDetailFragmentViewModel, FragmentComicDetailBinding>() {

    private lateinit var comicDetailFragmentArgs: ComicDetailFragmentArgs
    private lateinit var menuItem: MenuItem

    @Inject
    lateinit var urlListFragmentAdapter: UrlListFragmentAdapter

    @Inject
    lateinit var charactersListFragmentAdapter: PersonListFragmentAdapter

    @Inject
    lateinit var creatorsListFragmentAdapter: PersonListFragmentAdapter

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding =
        FragmentComicDetailBinding::inflate

    override val viewModelClass = ComicDetailFragmentViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        getComicDetailFragmentArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startOfLoading()
        setupRecyclerViews()
        viewModel.retrieveComicDetail(comicDetailFragmentArgs.comicId)
        observeEvents()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menuItem = menu.findItem(R.id.activity_main_menu_favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.activity_main_menu_favorite -> {
            if (viewModel.setFavorite()) {
                item.setIcon(R.drawable.ic_favorite_white)
            } else {
                item.setIcon(R.drawable.ic_not_favorite)
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun getComicDetailFragmentArgs() {
        val args: ComicDetailFragmentArgs by navArgs()
        comicDetailFragmentArgs = args
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

    private fun observeEvents() {
        viewModel.getComicLiveData().observeSafe(viewLifecycleOwner) {
            fillDetail(it)
            endOfLoading()
        }
        viewModel.getSavedComicLiveEvent().observeSafe(viewLifecycleOwner) {
            displaySuccessSnackbar(getString(R.string.added_comic, it))
        }
        viewModel.getRemovedComicLiveEvent().observeSafe(viewLifecycleOwner) {
            displaySuccessSnackbar(getString(R.string.removed_comic, it))
        }
        viewModel.getErrorLiveEvent().observeSafe(viewLifecycleOwner) {
            displayErrorSnackbar(it)
        }
    }

    private fun displaySuccessSnackbar(text: String) {
        snackbarComponent.displaySuccess(requireContext(), text, view)
    }

    private fun displayErrorSnackbar(exception: Exception) {
        snackbarComponent.displayError(requireContext(), exception, view)
    }

    private fun fillDetail(comicDataWrapper: ComicDataWrapper) {
        binding {
            Glide.with(requireContext())
                .load(comicDataWrapper.getImage())
                .placeholder(R.drawable.ic_image)
                .override(IMAGE_WIDTH, IMAGE_HEIGHT)
                .into(comicDetailImage)
            comicDetailTitle.text = comicDataWrapper.getTitle()
            if (comicDataWrapper.getDescription().isEmpty()) {
                comicDetailDescription.hide()
            } else {
                comicDetailDescriptionText.show()
                comicDetailDescription.text = comicDataWrapper.getDescription()
            }
            comicDetailPageCount.text = comicDataWrapper.getPageCount().toString()
            if (comicDataWrapper.getUrls().isEmpty()) {
                comicDetailUrlsRecyclerView.hide()
            } else {
                comicDetailUrlsText.show()
                urlListFragmentAdapter.setItems(comicDataWrapper.getUrls())
            }
            if (comicDataWrapper.getCharacters().isEmpty()) {
                comicDetailCharactersRecyclerView.hide()
            } else {
                comicDetailCharactersText.show()
                charactersListFragmentAdapter.setItems(comicDataWrapper.getCharacters())
            }
            if (comicDataWrapper.getCreators().isEmpty()) {
                comicDetailCreatorsRecyclerView.hide()
            } else {
                comicDetailCreatorsText.show()
                creatorsListFragmentAdapter.setItems(comicDataWrapper.getCreators())
            }
            if (comicDataWrapper.getFavorite()) {
                menuItem.setIcon(R.drawable.ic_favorite_white)
            } else {
                menuItem.setIcon(R.drawable.ic_not_favorite)
            }
        }
    }

    private fun endOfLoading() {
        binding {
            comicDetailTitleText.show()
            comicDetailPageCountText.show()
            comicDetailProgressBar.hide()
            menuItem.show()
        }
    }

}