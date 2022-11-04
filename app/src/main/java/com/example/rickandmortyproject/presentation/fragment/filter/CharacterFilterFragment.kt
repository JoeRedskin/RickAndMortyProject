package com.example.rickandmortyproject.presentation.fragment.filter

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.base.BaseFragment
import com.example.rickandmortyproject.databinding.CharacterFilterBinding
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.character.CharacterComponent
import com.example.rickandmortyproject.presentation.fragment.list.CharacterListFragment.Companion.filter_key
import com.example.rickandmortyproject.presentation.viewmodel.CharacterViewModel
import com.google.android.material.chip.Chip

class CharacterFilterFragment :
    BaseFragment<CharacterFilterBinding, CharacterViewModel>(R.layout.character_filter,
        CharacterViewModel::class.java) {

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
        binding.statusItems.findViewById<Chip>(binding.statusItems.checkedChipId)?.text.let {
            if (it != null) viewModel.queryStatus = it.toString()
            else viewModel.queryStatus = ""
        }
        binding.genderItems.findViewById<Chip>(binding.genderItems.checkedChipId)?.text.let {
            if (it != null) viewModel.queryGender = it.toString()
            else viewModel.queryGender = ""
        }
        viewModel.querySpecies = binding.filterSpecies.text.toString()
        viewModel.queryType = binding.filterType.text.toString()
    }

    override fun showUpButton() = true

    override fun setFragmentTitle() = R.string.filter_fragment_title

    override fun initBinding(view: View) = CharacterFilterBinding.bind(view)

    override fun initDaggerComponent(dependenciesProvider: DependenciesProvider) {
        CharacterComponent.init(dependenciesProvider).inject(this)
    }

    companion object {
        fun newInstance() = CharacterFilterFragment()
    }


}