package com.example.rickandmortyproject.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.databinding.CharacterItemBinding
import com.example.rickandmortyproject.utils.loadImage

class CharacterViewHolder(val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        val context = binding.root.context
        binding.name.text = character.name
        binding.statusSpecies.text =
            context.getString(R.string.status_species, character.status, character.species)
        binding.gender.text = character.gender
        binding.lastLocation.text = character.lastLocationName
        binding.originLocation.text = character.originLocationName
        loadImage(character.imageUrl, binding.imageView, context)
    }
}