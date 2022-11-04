package com.example.rickandmortyproject.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyproject.data.model.Location

class LocationDiffUtil : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}