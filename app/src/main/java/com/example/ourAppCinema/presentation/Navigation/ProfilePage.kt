package com.example.ourAppCinema.presentation.Navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel

@Composable
fun ProfilePage(viewModel: MoviesViewModel, navController: NavHostController) {
    Column {

Row{
        Button(onClick = { navController.navigate("liked") }) {
            Text("See Liked Films")
        }
        Button(onClick = { navController.navigate("favorites") }) {
            Text("See Favorite Films")
        }
    }}
}
