package com.marchenaya.marvelcomics.ui.comicList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.fragment.BaseVMFragment
import com.marchenaya.marvelcomics.component.network.NetworkManager
import com.marchenaya.marvelcomics.component.snackbar.SnackbarComponent
import com.marchenaya.marvelcomics.databinding.FragmentComicListBinding
import com.marchenaya.marvelcomics.extensions.observeSafe
import com.marchenaya.marvelcomics.ui.comicList.comicItem.ComicListFragmentAdapter
import com.marchenaya.marvelcomics.ui.comicList.loadItem.ComicLoadStateAdapter
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class ComicListFragment : BaseVMFragment<ComicListFragmentViewModel, FragmentComicListBinding>() {

    private var query = ""
    private var checkedChip = false

    @Inject
    lateinit var navigatorListener: ComicListFragmentNavigatorListener

    @Inject
    lateinit var comicListFragmentAdapter: ComicListFragmentAdapter

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicListBinding =
        FragmentComicListBinding::inflate

    override val viewModelClass = ComicListFragmentViewModel::class

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.activity_main_menu_search)
        setSearchView(menuItem.actionView as SearchView)
    }

    private fun setSearchView(searchView: SearchView) {
        searchView.queryHint = getString(R.string.search_menu_button)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    query = it
                    getComics(it, checkedChip)
                }
                return false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initGetComics()
        observeEvents()
        setupRecyclerViewScroll()
    }

    private fun initAdapter() {
        val currentBinding = binding
        with(currentBinding) {
            comicRecyclerView.apply {
                adapter =
                    comicListFragmentAdapter.withLoadStateHeaderAndFooter(
                        header = ComicLoadStateAdapter(comicListFragmentAdapter::retry),
                        footer = ComicLoadStateAdapter(comicListFragmentAdapter::retry)
                    )
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            comicListFragmentAdapter.apply {
                onItemClickListener = {
                    navigatorListener.displayFoodDetailFragment(it.getId(), it.getTitle())
                }
                addLoadStateListener { loadStates ->
                    onLoadStateListener(loadStates, currentBinding)
                }
            }
        }
    }

    private fun onLoadStateListener(
        loadStates: CombinedLoadStates,
        currentBinding: FragmentComicListBinding
    ) {
        with(currentBinding) {
            val isListEmpty =
                loadStates.refresh is LoadState.NotLoading && comicListFragmentAdapter.itemCount == 0
            comicEmptyList.isVisible = isListEmpty
            comicRecyclerView.isVisible = !isListEmpty
            comicChip.isVisible =
                loadStates.source.refresh is LoadState.NotLoading || comicListFragmentAdapter.itemCount > 0
            val isLoading = loadStates.source.refresh is LoadState.Loading
            comicProgressBar.isVisible = isLoading
        }

        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error
            ?: loadStates.append as? LoadState.Error
            ?: loadStates.prepend as? LoadState.Error
        errorState?.let {
            snackbarComponent.displayError(requireContext(), it.error, view)
        }
    }

    private fun initGetComics() {
        if (!networkManager.checkInternetConnectivity()) {
            getComics()
        }
    }

    private fun observeEvents() {
        networkManager.getConnectivityManager().observeSafe(viewLifecycleOwner) {
            getComics()
        }
        binding.comicChip.setOnCheckedChangeListener { _, checked ->
            checkedChip = checked
            getComics(query, checkedChip)
        }
        getComics()
    }

    private fun setupRecyclerViewScroll() {
        val currentBinding = binding
        lifecycleScope.launch {
            comicListFragmentAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { currentBinding.comicRecyclerView.scrollToPosition(0) }
        }
    }

    private fun getComics(query: String = "", filterByFavorite: Boolean = false) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getComics(query, filterByFavorite).collect {
                comicListFragmentAdapter.submitData(it)
            }
        }
    }

}