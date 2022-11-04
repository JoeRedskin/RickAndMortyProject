package com.example.rickandmortyproject.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.databinding.EpisodeItemBinding

class EpisodeViewHolder(val binding: EpisodeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(episode: Episode) {
        binding.name.text = episode.name
        binding.airDate.text = episode.airDate
        binding.episodeCode.text = episode.episodeCode
    }
}