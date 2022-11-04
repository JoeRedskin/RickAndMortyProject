package com.example.rickandmortyproject.presentation.fragment.filter

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.databinding.EpisodeFilterBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.episode.EpisodeComponent
import com.example.rickandmortyproject.presentation.fragment.list.EpisodeListFragment.Companion.filter_key
import com.example.rickandmortyproject.presentation.viewmodel.EpisodeViewModel

class EpisodeFilterFragment :
    BaseFragment<EpisodeFilterBinding, EpisodeViewModel>(R.layout.episode_filter,
        EpisodeViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFilter.setOnClickListener { saveFilterAndReturn() }
    }

    private fun saveFilterAndReturn() {
        saveFilterParams()
        setFragmentResult(filter_key, bundleOf(filter_key to viewModel.getQuery()))
        parentFragmentManager.popBackStack()
    }

    private fun saveFilterParams() {
        viewModel.queryCode = binding.filterEpisodeCode.text.toString()
    }

    override fun showUpButton() = true

    override fun setFragmentTitle() = R.string.filter_fragment_title

    override fun initBinding(view: View) = EpisodeFilterBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        EpisodeComponent.init(dependenciesProvider).inject(this)
    }

    companion object {
        fun newInstance() = EpisodeFilterFragment()
    }

}
