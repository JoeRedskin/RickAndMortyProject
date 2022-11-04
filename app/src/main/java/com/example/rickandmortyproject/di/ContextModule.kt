package com.example.rickandmortyproject.di

import android.content.Context

class ContextModule {

    fun provideContext(context: Context) : Context {
        return context.applicationContext
    }
}