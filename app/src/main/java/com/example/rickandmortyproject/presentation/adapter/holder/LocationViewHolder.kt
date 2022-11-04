package com.example.rickandmortyproject.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.databinding.LocationItemBinding

class LocationViewHolder(val binding: LocationItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        binding.name.text = location.name
        binding.dimension.text = location.dimension
        binding.type.text = location.type
    }
}