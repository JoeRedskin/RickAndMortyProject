package com.example.rickandmortyproject.di.network

import com.example.rickandmortyproject.data.service.RickAndMortyService

interface NetworkProvider {
    fun provideRickAndMortyService(): RickAndMortyService
}