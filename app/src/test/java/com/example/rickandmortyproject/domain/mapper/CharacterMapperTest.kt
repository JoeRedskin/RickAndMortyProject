package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.service.dto.CharacterDto
import com.example.rickandmortyproject.data.service.dto.LocationDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CharacterMapperTest : BehaviorSpec({

    val sut = CharacterMapper()
    Given("mapDtoToModel is correct") {
        val expectedData = Character(id = 6,
            name = "Abadango Cluster Princess",
            status = "Alive",
            species = "Alien",
            type = "",
            gender = "Female",
            originLocationName = "Abadango",
            originLocationId = 2,
            lastLocationName = "Abadango",
            lastLocationId = 2,
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            episodes = listOf("https://rickandmortyapi.com/api/episode/27"))
        val incomingData = CharacterDto(id = 6,
            name = "Abadango Cluster Princess",
            status = "Alive",
            species = "Alien",
            type = "",
            gender = "Female",
            originLocation = LocationDto(locationUrl = "https://rickandmortyapi.com/api/location/2",
                name = "Abadango"),
            lastLocation = LocationDto(locationUrl = "https://rickandmortyapi.com/api/location/2",
                name = "Abadango"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            episodes = listOf("https://rickandmortyapi.com/api/episode/27"),
            url = "https://rickandmortyapi.com/api/character/6",
            created = "2017-11-10T12:56:33.916Z")

        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected character") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty incoming data") {
        val expectedData = Character()
        val incomingData = CharacterDto()
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected empty character") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty location lists incoming data") {
        val expectedData = Character(id = 6,
            name = "Abadango Cluster Princess",
            status = "Alive",
            species = "Alien",
            type = "",
            gender = "Female",
            originLocationName = "unknown",
            originLocationId = 0,
            lastLocationName = "unknown",
            lastLocationId = 0,
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            episodes = listOf())
        val incomingData = CharacterDto(id = 6,
            name = "Abadango Cluster Princess",
            status = "Alive",
            species = "Alien",
            type = "",
            gender = "Female",
            originLocation = LocationDto(locationUrl = "", name = "unknown"),
            lastLocation = LocationDto(locationUrl = "", name = "unknown"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            episodes = listOf())
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected character") {
                actualData shouldBe expectedData
            }
        }
    }
})
