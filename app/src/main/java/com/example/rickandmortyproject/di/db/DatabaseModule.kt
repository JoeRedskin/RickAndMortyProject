package com.example.rickandmortyproject.di.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyproject.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
            AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    private const val DB_NAME = "RickAndMorty.db"
}
