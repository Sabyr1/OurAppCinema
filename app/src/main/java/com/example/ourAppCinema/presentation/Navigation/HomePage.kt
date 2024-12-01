package com.example.ourAppCinema.presentation.Navigation

import com.example.ourAppCinema.ui.MovieCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel
import com.example.ourAppCinema.ui.Oskars

@Composable
fun HomePage(navController: NavController, viewModel: MoviesViewModel = viewModel()) {
    val popular by viewModel.popularState10.collectAsState()
    val oscar by viewModel.getOscarState.collectAsState()
    val top250 by viewModel.top250State10.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(oscar) { movie ->
                    Oskars(movie,navController)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Популярное",
                    color = Color.Black,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "ВСЕ",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("list_popular")                         }
                )
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(popular) { movie ->
                    MovieCard(movie,navController)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Топ Фильмы",
                    color = Color.Black,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "ВСЕ",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("list_top250")
                        }
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(top250) { movie ->
                    MovieCard(movie,navController)
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}


