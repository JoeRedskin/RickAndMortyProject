package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.service.dto.LocationDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LocationMapperTest : BehaviorSpec({

    val sut = LocationMapper()
    Given("mapDtoToModel is correct") {
        val expectedData = Location(
            id = 7,
            name = "Immortality Field Resort",
            type = "Resort",
            dimension = "unknown",
            residents = listOf("https://rickandmortyapi.com/api/character/23",
                "https://rickandmortyapi.com/api/character/204",
                "https://rickandmortyapi.com/api/character/320"),
        )
        val incomingData = LocationDto(id = 7,
            name = "Immortality Field Resort",
            type = "Resort",
            dimension = "unknown",
            residents = listOf("https://rickandmortyapi.com/api/character/23",
                "https://rickandmortyapi.com/api/character/204",
                "https://rickandmortyapi.com/api/character/320"),
            locationUrl = "https://rickandmortyapi.com/api/location/7",
            created = "2017-11-10T13:09:17.136Z")

        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected location") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty incoming data") {
        val expectedData = Location()
        val incomingData = LocationDto()
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected empty location") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty location lists incoming data") {
        val expectedData = Location(
            id = 7,
            name = "Immortality Field Resort",
            type = "Resort",
            dimension = "unknown",
            residents = listOf(),
        )
        val incomingData = LocationDto(id = 7,
            name = "Immortality Field Resort",
            type = "Resort",
            dimension = "unknown",
            residents = listOf())
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected location") {
                actualData shouldBe expectedData
            }
        }
    }
})
