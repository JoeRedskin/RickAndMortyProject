package com.example.rickandmortyproject.domain.mapper

import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.service.dto.EpisodeDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class EpisodeMapperTest : BehaviorSpec({

    val sut = EpisodeMapper()
    Given("mapDtoToModel is correct") {
        val expectedData = Episode(
            id = 2,
            name = "Lawnmower Dog",
            airDate = "December 9, 2013",
            episodeCode = "S01E02",
            characters = listOf("https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2"),
        )
        val incomingData = EpisodeDto(id = 2,
            name = "Lawnmower Dog",
            airDate = "December 9, 2013",
            episodeCode = "S01E02",
            characters = listOf("https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2"),
            episodeUrl = "https://rickandmortyapi.com/api/episode/2",
            created = "2017-11-10T12:56:33.916Z")

        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected episode") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty incoming data") {
        val expectedData = Episode()
        val incomingData = EpisodeDto()
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected empty episode") {
                actualData shouldBe expectedData
            }
        }
    }

    Given("mapDtoToModel with empty location lists incoming data") {
        val expectedData = Episode(
            id = 2,
            name = "Lawnmower Dog",
            airDate = "December 9, 2013",
            episodeCode = "S01E02",
            characters = listOf(),
        )
        val incomingData = EpisodeDto(id = 2,
            name = "Lawnmower Dog",
            airDate = "December 9, 2013",
            episodeCode = "S01E02",
            characters = listOf(),
            episodeUrl = "https://rickandmortyapi.com/api/episode/2",
            created = "2017-11-10T12:56:33.916Z")
        When("mapDtoToModel called") {
            val actualData = sut.mapDtoToModel(incomingData)
            Then("should get expected episode") {
                actualData shouldBe expectedData
            }
        }
    }
})
