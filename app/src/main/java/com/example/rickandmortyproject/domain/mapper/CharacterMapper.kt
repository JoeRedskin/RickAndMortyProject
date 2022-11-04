package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.service.dto.CharacterDto
import com.example.rickandmortyproject.utils.getId

class CharacterMapper : Mapper<CharacterDto, Character> {

    override fun mapDtoToModel(dto: CharacterDto): Character {
        return Character(id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            originLocationId = getId(dto.originLocation.locationUrl),
            originLocationName = dto.originLocation.name,
            lastLocationId = getId(dto.lastLocation.locationUrl),
            lastLocationName = dto.lastLocation.name,
            imageUrl = dto.imageUrl,
            episodes = dto.episodes)
    }
}