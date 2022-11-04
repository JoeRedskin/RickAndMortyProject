package com.example.rickandmortyproject.domain.mapper

interface Mapper <ModelDto, Model> {

    fun mapDtoToModel(dto: ModelDto) : Model
}