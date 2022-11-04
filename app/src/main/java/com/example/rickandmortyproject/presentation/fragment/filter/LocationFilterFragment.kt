package com.example.rickandmortyproject.presentation.fragment.filter

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.databinding.LocationFilterBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.location.LocationComponent
import com.example.rickandmortyproject.presentation.fragment.list.LocationListFragment.Companion.filter_key
import com.example.rickandmortyproject.presentation.viewmodel.LocationViewModel

class LocationFilterFragment :
    BaseFragment<LocationFilterBinding, LocationViewModel>(R.layout.location_filter,
        LocationViewModel::class.java) {

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
        viewModel.queryDimension = binding.filterDimension.text.toString()
        viewModel.queryType = binding.filterType.text.toString()
    }

    override fun showUpButton() = true

    override fun setFragmentTitle() = R.string.filter_fragment_title

    override fun initBinding(view: View) = LocationFilterBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        LocationComponent.init(dependenciesProvider).inject(this)
    }

    companion object {
        fun newInstance() = LocationFilterFragment()
    }
}