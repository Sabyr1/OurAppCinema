package com.example.ourAppCinema.presentation.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ourAppCinema.ui.MovieCard
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel




@Composable
fun AllPopGrid(viewModel: MoviesViewModel = viewModel(), navController: NavController) {
    val popular by viewModel.popularState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
            ) {
                items(popular) { movie ->
                    MovieCard(movie, navController)
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))

    }
}



@Composable
fun All250Grid(viewModel: MoviesViewModel = viewModel(), navController: NavController) {
    val top250 by viewModel.top250State.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 40.dp)
            ) {
                items(top250) { movie ->
                    MovieCard(movie,navController)
                }
            }
        }
    }


}

