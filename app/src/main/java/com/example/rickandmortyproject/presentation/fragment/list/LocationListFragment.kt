package com.example.rickandmortyproject.presentation.fragment.list

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.utils.FragmentTransfer
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.databinding.FragmentListBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.location.LocationComponent
import com.example.rickandmortyproject.presentation.adapter.LocationAdapter
import com.example.rickandmortyproject.presentation.adapter.loadstate.LoadStateAdapter
import com.example.rickandmortyproject.presentation.viewmodel.LocationViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationListFragment : BaseFragment<FragmentListBinding, LocationViewModel>(
    R.layout.fragment_list,
    LocationViewModel::class.java) {

    private lateinit var adapter: LocationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setProgressIndicators()
        setDataInAdapter()
        setRefreshLayout()
        setMenu(Location::class.java) { setSearchView(it) }
        setFilterResultListener()
    }

    private fun setAdapter() {
        adapter = LocationAdapter {
            FragmentTransfer().goToDetailsFragment(it.id, it::class.java, parentFragmentManager)
        }
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(LoadStateAdapter { adapter.retry() })
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setProgressIndicators() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                val isListEmpty = it.refresh is LoadState.Error
                        || (it.refresh is LoadState.NotLoading && adapter.itemCount == 0)
                binding.emptyList.isVisible = isListEmpty
                binding.recyclerView.isVisible = !isListEmpty
                binding.progressStart.isVisible = it.source.refresh is LoadState.Loading
                binding.progressPageLoading.isVisible = it.source.append is LoadState.Loading
            }
        }
    }

    private fun setDataInAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchLocations().collectLatest {
                binding.recyclerView.scrollToPosition(0)
                adapter.submitData(it)
            }
        }
    }

    private fun setRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            setDataInAdapter()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setSearchView(searchItem: MenuItem) {
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.query_hint_location)
        searchView.isFocusable = false

        if (viewModel.queryName.isNotEmpty()) {
            searchView.onActionViewExpanded()
            searchView.setQuery(viewModel.queryName, false)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (viewLifecycleOwnerLiveData.value != null && newText != null) {
                    viewModel.queryName = newText
                    setDataInAdapter()
                }
                return false
            }
        })
    }

    private fun setFilterResultListener() {
        setFragmentResultListener(filter_key) { requestKey, bundle ->
            val result = bundle.getParcelable<Location>(requestKey)
            if (result != null) viewModel.setQuery(result)
            setDataInAdapter()
        }
    }

    override fun showUpButton() = false

    override fun setFragmentTitle() = R.string.location_fragment_title

    override fun initBinding(view: View) = FragmentListBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        LocationComponent.init(dependenciesProvider).inject(this)
    }

    companion object {
        const val filter_key = "filter locations"
        fun newInstance() = LocationListFragment()
    }
}