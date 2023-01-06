package com.example.pokedex_compose.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex_compose.R
import com.example.pokedex_compose.di.Injection
import com.example.pokedex_compose.model.Pokemon
import com.example.pokedex_compose.ui.ViewModelFactory
import com.example.pokedex_compose.ui.common.UiState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPokemon()
            }
            is UiState.Success -> {
                HomeContent(
                    pokemonList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    pokemonList: List<Pokemon>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {

    Box(modifier = modifier) {
        Column {
/*            SearchBar(
                query = query,
                onQueryChange = viewModel::search,
                modifier = Modifier.background(MaterialTheme.colors.primary)
            )*/
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                items(pokemonList) { data ->
                    PokemonItem(
                        id = data.id,
                        name = data.name,
                        img = data.img,
                        navigateToDetail = navigateToDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    img: String,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .clickable {
                navigateToDetail(id)
                Log.d("Hobby", "Pressed Box")
            }
            .padding(10.dp)
            .width(180.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp, 150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = name,
                fontFamily = FontFamily(Font(resId = R.font.poppins_light)),
                color = Color.Black,
            )

        }
    }
}