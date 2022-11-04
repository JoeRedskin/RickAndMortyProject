package com.example.rickandmortyproject.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyproject.data.model.Episode

class EpisodeDiffUtil : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}