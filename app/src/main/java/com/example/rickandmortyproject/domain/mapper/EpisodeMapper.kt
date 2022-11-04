package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.service.dto.EpisodeDto

class EpisodeMapper : Mapper<EpisodeDto, Episode> {

    override fun mapDtoToModel(dto: EpisodeDto): Episode {
        return Episode(id = dto.id,
            name = dto.name,
            airDate = dto.airDate,
            episodeCode = dto.episodeCode,
            characters = dto.characters)
    }
}