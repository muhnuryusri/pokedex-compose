package com.example.pokedex_compose.di

import com.example.pokedex_compose.data.PokemonRepository

object Injection {
    fun provideRepository(): PokemonRepository {
        return PokemonRepository.getInstance()
    }
}