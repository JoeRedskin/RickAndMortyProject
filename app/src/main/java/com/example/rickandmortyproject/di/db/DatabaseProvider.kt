package com.example.rickandmortyproject.di.db

import com.example.rickandmortyproject.data.db.AppDatabase

interface DatabaseProvider {
    fun provideAppDatabase(): AppDatabase
}