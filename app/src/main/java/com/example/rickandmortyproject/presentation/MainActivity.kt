package com.example.rickandmortyproject.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.databinding.ActivityMainBinding
import com.example.rickandmortyproject.presentation.fragment.list.CharacterListFragment
import com.example.rickandmortyproject.presentation.fragment.list.EpisodeListFragment
import com.example.rickandmortyproject.presentation.fragment.list.LocationListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterListFragment = CharacterListFragment.newInstance()
        val locationListFragment = LocationListFragment.newInstance()
        val episodeListFragment = EpisodeListFragment.newInstance()

        if (savedInstanceState == null) {
            setCurrentFragment(characterListFragment)
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_characters -> setCurrentFragment(characterListFragment)
                R.id.action_locations -> setCurrentFragment(locationListFragment)
                R.id.action_episodes -> setCurrentFragment(episodeListFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.container, fragment)
        }

    fun showUpButton(boolean: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}