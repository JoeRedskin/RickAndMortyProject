package com.example.rickandmortyproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.databinding.CharacterItemBinding
import com.example.rickandmortyproject.presentation.adapter.diffutil.CharacterDiffUtil
import com.example.rickandmortyproject.presentation.adapter.holder.CharacterViewHolder

class CharacterAdapter(private val onClickAction: (Character) -> Unit) :
    PagingDataAdapter<Character, CharacterViewHolder>(CharacterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.binding.root.setOnClickListener {
                onClickAction(item)
            }
        }
    }
}