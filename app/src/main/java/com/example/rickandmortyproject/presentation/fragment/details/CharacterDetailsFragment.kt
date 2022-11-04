package com.example.rickandmortyproject.presentation.fragment.details

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.utils.FragmentTransfer
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.databinding.CharacterDetailsBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.character.CharacterDetailsComponent
import com.example.rickandmortyproject.presentation.adapter.EpisodeDetailsAdapter
import com.example.rickandmortyproject.presentation.viewmodel.CharacterDetailsViewModel
import com.example.rickandmortyproject.utils.loadImage
import kotlinx.coroutines.launch


class CharacterDetailsFragment :
    BaseFragment<CharacterDetailsBinding, CharacterDetailsViewModel>(R.layout.character_details,
        CharacterDetailsViewModel::class.java) {

    private lateinit var adapter: EpisodeDetailsAdapter
    private val characterId: Int by lazy {
        arguments?.getInt(ARG_CHARACTER) ?: 0
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemData()
        setAdapter()
        setItemDecorator()
    }

    private fun setItemData() {
        binding.progressStart.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.character.observe(viewLifecycleOwner) { character ->
                val context = binding.root.context
                binding.progressStart.isVisible = false
                binding.name.text = character.name
                binding.statusSpecies.text =
                    context.getString(R.string.status_species, character.status, character.species)
                binding.gender.text = character.gender
                binding.lastLocation.text = character.lastLocationName
                binding.originLocation.text = character.originLocationName

                if (character.type.isNotEmpty()) {
                    binding.typeText.visibility = View.VISIBLE
                    binding.type.visibility = View.VISIBLE
                    binding.type.text = character.type
                }

                loadImage(character.imageUrl, binding.characterImageDetails, context)
                setLocationListeners(character.lastLocationId, character.originLocationId)
            }
        }
    }

    private fun setLocationListeners(lastLocationId: Int, originLocationId: Int) {
        if (lastLocationId != 0)
            binding.lastLocation.setOnClickListener {
                FragmentTransfer().goToDetailsFragment(lastLocationId,
                    Location::class.java,
                    parentFragmentManager)
            }

        if (originLocationId != 0)
            binding.originLocation.setOnClickListener {
                FragmentTransfer().goToDetailsFragment(originLocationId,
                    Location::class.java,
                    parentFragmentManager)
            }
    }

    private fun setAdapter() {
        adapter = EpisodeDetailsAdapter {
            FragmentTransfer().goToDetailsFragment(it.id, it::class.java, parentFragmentManager)
        }
        binding.episodesRecycler.adapter = adapter
        setDataInAdapter()
    }

    private fun setDataInAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodes.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    private fun setItemDecorator() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.root.context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        binding.episodesRecycler.addItemDecoration(dividerItemDecoration)
    }

    override fun showUpButton() = true

    override fun setFragmentTitle() = R.string.character_details_fragment_title

    override fun initBinding(view: View) = CharacterDetailsBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        CharacterDetailsComponent.init(characterId, dependenciesProvider).inject(this)
    }

    companion object {
        const val ARG_CHARACTER = "CHARACTER"

        fun newInstance(id: Int) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER, id)
                }
            }
    }
}