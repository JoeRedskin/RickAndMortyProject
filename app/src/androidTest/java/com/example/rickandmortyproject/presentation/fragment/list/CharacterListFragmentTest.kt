package com.example.rickandmortyproject.presentation.fragment.list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.presentation.MainActivity
import com.example.rickandmortyproject.presentation.adapter.holder.CharacterViewHolder
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListFragmentTest {

    @Test
    fun testNavigateToEpisodeFilterFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.action_episodes)).perform(click())
        onView(withId(R.id.action_filter)).perform(click())
        onView(withId(R.id.button_filter)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigateToCharacterDetailsFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.recycler_view)).perform(

                RecyclerViewActions.actionOnItemAtPosition<CharacterViewHolder>(
                    0,
                    click()
                ))
    onView(withId(R.id.characterImageDetails)).check(matches(isDisplayed()))
    }
}