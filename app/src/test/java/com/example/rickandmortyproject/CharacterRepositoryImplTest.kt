package com.example.rickandmortyproject

import android.util.Log
import com.example.rickandmortyproject.data.db.AppDatabase
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyproject.data.service.RickAndMortyService
import com.example.rickandmortyproject.data.service.dto.CharacterDto
import com.example.rickandmortyproject.data.service.dto.LocationDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic

class CharacterRepositoryImplTest : BehaviorSpec({

    mockkStatic(Log::class)
    every { Log.v(any(), any()) } returns 0
    every { Log.d(any(), any()) } returns 0
    every { Log.i(any(), any()) } returns 0
    every { Log.e(any(), any()) } returns 0

    coroutineTestScope = true

    val service = mockk<RickAndMortyService>()
    val db = mockk<AppDatabase>()
    val characterRepository = CharacterRepositoryImpl(service, db)

    val id = 6
    val incorrectId = -1
    val expectedResponse = Character(id = 6,
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
    val incomingResponse = CharacterDto(id = 6,
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
        url = "",
        created = "")

    Given("getCharacterById is correct") {
        coEvery { service.getCharacterById(id) } returns incomingResponse
        coEvery { db.characterDao().getById(id) } returns expectedResponse
        When("getCharacterById called") {
            val actualResponse = characterRepository.getCharacterById(id)
            Then("should get Abadango character") {
                actualResponse shouldBe expectedResponse
            }
        }
    }

    Given("getCharacterById with incorrect network") {
        coEvery { service.getCharacterById(id) } throws Exception()
        coEvery { db.characterDao().getById(id) } returns expectedResponse
        When("getCharacterById called") {
            val actualResponse = characterRepository.getCharacterById(id)
            Then("should get data in database") {
                actualResponse shouldBe expectedResponse
            }
        }
    }

    Given("getCharacterById with incorrect id") {
        coEvery { service.getCharacterById(incorrectId) } throws Exception()
        coEvery { db.characterDao().getById(incorrectId) } throws Exception()
        When("getCharacterById called") {
            val actualResponse = characterRepository.getCharacterById(incorrectId)
            Then("should get null") {
                actualResponse shouldBe null
            }
        }
    }

    Given("getCharacterById with incorrect network and database") {
        coEvery { service.getCharacterById(id) } throws Exception()
        coEvery { db.characterDao().getById(id) } throws Exception()
        When("getCharacterById called") {
            val actualResponse = characterRepository.getCharacterById(id)
            Then("should get null") {
                actualResponse shouldBe null
            }
        }
    }


    Given("getCharactersByIds is correct") {
        coEvery { service.getCharactersByIds(listOf(id)) } returns listOf(incomingResponse)
        coEvery { db.characterDao().getByIds(listOf(id)) } returns listOf(expectedResponse)
        When("getCharactersByIds called") {
            val actualResponse = characterRepository.getCharactersByIds(listOf(id))
            Then("should get Abadango character") {
                actualResponse shouldBe listOf(expectedResponse)
            }
        }
    }

    Given("getCharactersByIds with incorrect network") {
        coEvery { service.getCharactersByIds(listOf(id)) } throws Exception()
        coEvery { db.characterDao().getByIds(listOf(id)) } returns listOf(expectedResponse)
        When("getCharactersByIds called") {
            val actualResponse = characterRepository.getCharactersByIds(listOf(id))
            Then("should get data in database") {
                actualResponse shouldBe listOf(expectedResponse)
            }
        }
    }

    Given("getCharactersByIds with incorrect id") {
        coEvery { service.getCharactersByIds(listOf(incorrectId)) } throws Exception()
        coEvery { db.characterDao().getByIds(listOf(incorrectId)) } throws Exception()
        When("getCharactersByIds called") {
            val actualResponse = characterRepository.getCharactersByIds(listOf(incorrectId))
            Then("should get null") {
                actualResponse shouldBe null
            }
        }
    }

    Given("getCharactersByIds with incorrect network and database") {
        coEvery { service.getCharactersByIds(listOf(id)) } throws Exception()
        coEvery { db.characterDao().getByIds(listOf(id)) } throws Exception()
        When("getCharactersByIds called") {
            val actualResponse = characterRepository.getCharactersByIds(listOf(incorrectId))
            Then("should get null") {
                actualResponse shouldBe null
            }
        }
    }
})
