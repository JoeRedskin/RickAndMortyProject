package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.service.dto.LocationDto

class LocationMapper : Mapper<LocationDto, Location> {

    override fun mapDtoToModel(dto: LocationDto): Location {
        return Location(id = dto.id,
            name = dto.name,
            type = dto.type,
            dimension = dto.dimension,
            residents = dto.residents)
    }
}