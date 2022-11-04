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
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.databinding.FragmentListBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.character.CharacterComponent
import com.example.rickandmortyproject.presentation.adapter.CharacterAdapter
import com.example.rickandmortyproject.presentation.adapter.loadstate.LoadStateAdapter
import com.example.rickandmortyproject.presentation.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListFragment : BaseFragment<FragmentListBinding, CharacterViewModel>(
    R.layout.fragment_list,
    CharacterViewModel::class.java) {

    private lateinit var adapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setProgressIndicators()
        setRefreshLayout()
        setMenu(Character::class.java) { setSearchView(it) }
        setFilterResultListener()
    }

    private fun setAdapter() {
        binding.progressPageLoading.isVisible = false
        binding.progressStart.isVisible = false
        adapter = CharacterAdapter {
            FragmentTransfer().goToDetailsFragment(it.id, it::class.java, parentFragmentManager)
        }
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(LoadStateAdapter { adapter.retry() })
        setDataInAdapter()
    }

    private fun setDataInAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchCharacters().collectLatest {
                binding.recyclerView.scrollToPosition(0)
                adapter.submitData(it)
            }
        }
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

    private fun setRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setSearchView(searchItem: MenuItem) {
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.query_hint_character)
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
            val result = bundle.getParcelable<Character>(requestKey)
            if (result != null) viewModel.setQuery(result)
            setDataInAdapter()
        }
    }

    override fun showUpButton() = false

    override fun setFragmentTitle() = R.string.character_fragment_title

    override fun initBinding(view: View) = FragmentListBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        CharacterComponent.init(dependenciesProvider).inject(this)
    }

    companion object {
        const val filter_key = "filter characters"
        fun newInstance() = CharacterListFragment()
    }

}