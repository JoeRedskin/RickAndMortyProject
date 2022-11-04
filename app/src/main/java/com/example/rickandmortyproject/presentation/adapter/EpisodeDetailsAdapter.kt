package com.example.rickandmortyproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.databinding.EpisodeItemBinding
import com.example.rickandmortyproject.presentation.adapter.diffutil.EpisodeDiffUtil
import com.example.rickandmortyproject.presentation.adapter.holder.EpisodeViewHolder

class EpisodeDetailsAdapter(
    private val onClickAction: (Episode) -> Unit,
) : ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EpisodeItemBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickAction(item)
        }
    }
}