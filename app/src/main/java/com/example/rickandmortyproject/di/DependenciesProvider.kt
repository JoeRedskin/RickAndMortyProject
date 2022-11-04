package com.example.rickandmortyproject.di

import com.example.rickandmortyproject.di.db.DatabaseProvider
import com.example.rickandmortyproject.di.network.NetworkProvider

interface DependenciesProvider : NetworkProvider, DatabaseProvider