package com.example.rickandmortyproject.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyproject.data.model.Character

class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}