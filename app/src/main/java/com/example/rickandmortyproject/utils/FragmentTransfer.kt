package com.example.rickandmortyproject.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.model.Model
import com.example.rickandmortyproject.presentation.fragment.details.CharacterDetailsFragment
import com.example.rickandmortyproject.presentation.fragment.details.EpisodeDetailsFragment
import com.example.rickandmortyproject.presentation.fragment.details.LocationDetailsFragment
import com.example.rickandmortyproject.presentation.fragment.filter.CharacterFilterFragment
import com.example.rickandmortyproject.presentation.fragment.filter.EpisodeFilterFragment
import com.example.rickandmortyproject.presentation.fragment.filter.LocationFilterFragment

class FragmentTransfer {

    fun goToDetailsFragment(
        itemId: Int,
        itemType: Class<out Model>,
        fragmentManager: FragmentManager,
    ) {
        var fragment = Fragment()
        when (itemType) {
            Character::class.java -> fragment = CharacterDetailsFragment.newInstance(itemId)
            Location::class.java -> fragment = LocationDetailsFragment.newInstance(itemId)
            Episode::class.java -> fragment = EpisodeDetailsFragment.newInstance(itemId)
        }
        setCurrentFragment(fragment, fragmentManager)
    }

    fun goToFilterView(itemType: Class<out Model>, fragmentManager: FragmentManager) {
        var fragment = Fragment()
        when (itemType) {
            Character::class.java -> fragment = CharacterFilterFragment.newInstance()
            Location::class.java -> fragment = LocationFilterFragment.newInstance()
            Episode::class.java -> fragment = EpisodeFilterFragment.newInstance()
        }
        setCurrentFragment(fragment, fragmentManager)
    }

    private fun setCurrentFragment(fragment: Fragment, fragmentManager: FragmentManager) =
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.container, fragment)
            addToBackStack(null)
        }
}