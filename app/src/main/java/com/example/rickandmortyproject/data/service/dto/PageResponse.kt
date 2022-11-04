package com.example.rickandmortyproject.data.service.dto

data class PageResponse<T>(
    val info: InfoDto,
    val results: List<T>,
)