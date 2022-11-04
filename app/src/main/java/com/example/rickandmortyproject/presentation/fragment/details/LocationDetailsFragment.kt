package com.example.rickandmortyproject.presentation.fragment.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.utils.FragmentTransfer
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.databinding.LocationDetailsBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.location.LocationDetailsComponent
import com.example.rickandmortyproject.presentation.adapter.CharacterDetailsAdapter
import com.example.rickandmortyproject.presentation.viewmodel.LocationDetailsViewModel
import kotlinx.coroutines.launch

class LocationDetailsFragment :
    BaseFragment<LocationDetailsBinding, LocationDetailsViewModel>(R.layout.location_details,
        LocationDetailsViewModel::class.java) {

    private lateinit var adapter: CharacterDetailsAdapter
    private val locationId: Int by lazy {
        arguments?.getInt(ARG_LOCATION) ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemData()
        setAdapter()
    }

    private fun setItemData() {
        binding.progressStart.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.location.observe(viewLifecycleOwner) { location ->
                binding.progressStart.isVisible = false
                binding.name.text = location.name
                binding.dimension.text = location.dimension
                binding.type.text = location.type
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

    override fun setFragmentTitle() = R.string.location_details_fragment_title

    override fun initBinding(view: View) = LocationDetailsBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        LocationDetailsComponent.init(locationId, dependenciesProvider).inject(this)
    }

    companion object {
        private const val ARG_LOCATION = "LOCATION"

        fun newInstance(id: Int) =
            LocationDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LOCATION, id)
                }
            }
    }
}
