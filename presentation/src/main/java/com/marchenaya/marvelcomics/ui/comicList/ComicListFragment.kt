package com.marchenaya.marvelcomics.ui.comicList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.fragment.BaseVMFragment
import com.marchenaya.marvelcomics.databinding.FragmentComicListBinding
import com.marchenaya.marvelcomics.extensions.hide
import com.marchenaya.marvelcomics.extensions.show
import com.marchenaya.marvelcomics.ui.comicList.item.ComicListFragmentAdapter
import com.marchenaya.marvelcomics.ui.comicList.load.ComicLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class ComicListFragment : BaseVMFragment<ComicListFragmentViewModel, FragmentComicListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicListBinding =
        FragmentComicListBinding::inflate

    override val viewModelClass = ComicListFragmentViewModel::class

    private val adapter = ComicListFragmentAdapter()

    private var searchJob: Job? = null

    private fun getComics(query: String = "") {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getComics(query).collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.activity_main_menu_search)
        val searchView = menuItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_menu_button)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?) = false
            override fun onQueryTextSubmit(newText: String?): Boolean {
                newText?.let {
                    getComics(it)
                }
                return false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getComics()
        initGetComics()

        binding.comicRetryButton.setOnClickListener { adapter.retry() }
    }

    private fun initAdapter() {
        binding {
            comicRecyclerView.adapter =
                adapter.withLoadStateHeaderAndFooter(header = ComicLoadStateAdapter { adapter.retry() },
                    footer = ComicLoadStateAdapter { adapter.retry() })

            comicRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            adapter.addLoadStateListener { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                showEmptyList(isListEmpty)

                comicRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                comicProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                comicRetryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        requireContext(),
                        "Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initGetComics() {
        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.comicRecyclerView.scrollToPosition(0) }
        }
    }

    private fun showEmptyList(show: Boolean) {
        binding {
            if (show) {
                comicEmptyList.show()
                comicRecyclerView.hide()
            } else {
                comicEmptyList.hide()
                comicRecyclerView.show()
            }
        }
    }

}