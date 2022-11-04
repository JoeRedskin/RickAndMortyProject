package com.example.rickandmortyproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.databinding.LocationItemBinding
import com.example.rickandmortyproject.presentation.adapter.diffutil.LocationDiffUtil
import com.example.rickandmortyproject.presentation.adapter.holder.LocationViewHolder

class LocationAdapter(
    private val onClickAction: (Location) -> Unit,
) : PagingDataAdapter<Location, LocationViewHolder>(LocationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(inflater, parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.binding.root.setOnClickListener {
                onClickAction(item)
            }
        }
    }
}