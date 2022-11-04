package com.example.rickandmortyproject.presentation.fragment.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.utils.FragmentTransfer
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.databinding.EpisodeDetailsBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.episode.EpisodeDetailsComponent
import com.example.rickandmortyproject.presentation.adapter.CharacterDetailsAdapter
import com.example.rickandmortyproject.presentation.viewmodel.EpisodeDetailsViewModel
import kotlinx.coroutines.launch

class EpisodeDetailsFragment :
    BaseFragment<EpisodeDetailsBinding, EpisodeDetailsViewModel>(R.layout.episode_details,
        EpisodeDetailsViewModel::class.java) {

    private lateinit var adapter: CharacterDetailsAdapter
    private val episodeId: Int by lazy {
        arguments?.getInt(ARG_EPISODE) ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemData()
        setAdapter()
    }

    private fun setItemData() {
        binding.progressStart.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episode.observe(viewLifecycleOwner) { episode ->
                binding.progressStart.isVisible = false
                binding.name.text = episode.name
                binding.airDate.text = episode.airDate
                binding.episodeCode.text = episode.episodeCode
            }
        }
    }

    private fun setAdapter() {
        adapter = CharacterDetailsAdapter {
            FragmentTransfer().goToDetailsFragment(it.id, it::class.java, parentFragmentManager)
        }
        binding.charactersRecycler.adapter = adapter
        setDataInAdapter()
    }

    private fun setDataInAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    override fun showUpButton() = true

    override fun setFragmentTitle() = R.string.episode_details_fragment_title

    override fun initBinding(view: View) = EpisodeDetailsBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        EpisodeDetailsComponent.init(episodeId, dependenciesProvider).inject(this)
    }

    companion object {
        private const val ARG_EPISODE = "EPISODE"

        fun newInstance(id: Int) =
            EpisodeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_EPISODE, id)
                }
            }
    }
}