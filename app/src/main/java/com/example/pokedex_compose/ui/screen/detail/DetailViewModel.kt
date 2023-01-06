package com.example.pokedex_compose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_compose.data.PokemonRepository
import com.example.pokedex_compose.model.Pokemon
import com.example.pokedex_compose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Pokemon>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Pokemon>>
        get() = _uiState

    fun getPokemonById(pokemonId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPokemonDetailById(pokemonId))
        }
    }
}