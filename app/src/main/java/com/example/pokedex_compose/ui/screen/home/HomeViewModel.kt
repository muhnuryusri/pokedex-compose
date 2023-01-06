package com.example.pokedex_compose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_compose.data.PokemonRepository
import com.example.pokedex_compose.model.Pokemon
import com.example.pokedex_compose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PokemonRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Pokemon>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pokemon>>>
        get() = _uiState

    fun getPokemon() {
        viewModelScope.launch {
            repository.getPokemon()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { pokemonList ->
                    _uiState.value = UiState.Success(pokemonList)
                }
        }
    }

    private val _query = MutableStateFlow("")
    val query: MutableStateFlow<String> get() = _query

    fun searchPokemon(newQuery: String) {
        viewModelScope.launch {
            repository.searchPokemon(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { pokemonList ->
                    _query.value = newQuery
                    _uiState.value = UiState.Success(pokemonList)
                }
        }
    }
}

