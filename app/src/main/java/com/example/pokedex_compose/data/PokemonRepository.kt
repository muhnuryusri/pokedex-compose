package com.example.pokedex_compose.data

import com.example.pokedex_compose.model.Pokemon
import com.example.pokedex_compose.model.PokemonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonRepository {
    private val pokemonList = mutableListOf<Pokemon>()

    init {
        if (pokemonList.isEmpty()) {
            PokemonData.pokemon.forEach {
                pokemonList.add(Pokemon(it.id, it.name, it.img, it.desc))
            }
        }
    }

    fun getPokemon(): Flow<List<Pokemon>> {
        return flowOf(pokemonList)
    }

    fun getPokemonDetailById(pokemonId: Int): Pokemon {
        return pokemonList.first {
            it.id == pokemonId
        }
    }

    companion object {
        @Volatile
        private var instance: PokemonRepository? = null

        fun getInstance(): PokemonRepository =
            instance ?: synchronized(this) {
                PokemonRepository().apply {
                    instance = this
                }
            }
    }
}