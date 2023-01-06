package com.example.pokedex_compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex_compose.ui.screen.home.HomeViewModel
import com.example.pokedex_compose.data.PokemonRepository
import com.example.pokedex_compose.ui.screen.detail.DetailViewModel

class ViewModelFactory(private val repository: PokemonRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((HomeViewModel::class.java))) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom((DetailViewModel::class.java))) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}