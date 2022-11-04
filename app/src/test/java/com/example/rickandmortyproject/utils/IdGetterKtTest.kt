package com.example.rickandmortyproject.utils

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class IdGetterKtTest : BehaviorSpec({

    Given("getId with correct input Url") {
        val expectedValue = 320
        val inputUrl = "https://rickandmortyapi.com/api/character/320"
        When("getId called") {
            val actualValue = getId(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getId with empty input Url") {
        val expectedValue = 0
        val inputUrl = ""
        When("getId called") {
            val actualValue = getId(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getId with incorrect input Url") {
        val expectedValue = 0
        val inputUrl = "https://rickandmortyapi.com/api/character/?status=alive"
        When("getId called") {
            val actualValue = getId(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getIdList with correct input list") {
        val expectedValue = listOf(23, 204, 320)
        val inputUrl = listOf("https://rickandmortyapi.com/api/character/23",
            "https://rickandmortyapi.com/api/character/204",
            "https://rickandmortyapi.com/api/character/320")
        When("getId called") {
            val actualValue = getIdList(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getIdList with empty input list") {
        val expectedValue = emptyList<Int>()
        val inputUrl = emptyList<String>()
        When("getId called") {
            val actualValue = getIdList(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getIdList with incorrect input list") {
        val expectedValue = listOf(0,0,0)
        val inputUrl = listOf("https://rickandmortyapi.com/api/character/?name=Rick",
            "https://rickandmortyapi.com/api/character/?name=Morty",
            "https://rickandmortyapi.com/api/character/?name=Sam")
        When("getId called") {
            val actualValue = getIdList(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }

    Given("getIdList with null input list") {
        val expectedValue = emptyList<Int>()
        val inputUrl = null
        When("getId called") {
            val actualValue = getIdList(inputUrl)
            Then("should get $expectedValue") {
                actualValue shouldBe expectedValue
            }
        }
    }
})
