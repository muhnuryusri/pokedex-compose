package com.example.pokedex_compose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Detail : Screen("home/{pokemonId}") {
        fun createRoute(pokemonId: Int) = "home/$pokemonId"
    }
}