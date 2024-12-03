package com.example.ourAppCinema.presentation.Navigation

import FilterScreen
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ourAppCinema.R
import com.example.ourAppCinema.data.model.SearchedData
import com.example.ourAppCinema.data.model.SimpleFilm
import com.example.ourAppCinema.data.model.search.Film
import com.example.ourAppCinema.data.model.search.SearchData
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel


@Composable
fun SearchingPage(viewModel: MoviesViewModel , navController: NavHostController  ) {
    var searchText by remember { mutableStateOf("") }
    val searchData = viewModel.searchData.collectAsState().value
    var showFilters by remember { mutableStateOf(false) }


    Column (modifier = Modifier .background(color = Color.White) .fillMaxSize()) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("  Фильмы , актеры , режиссеры") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(80.dp),
            trailingIcon = {
                IconButton(onClick = {showFilters = !showFilters} , modifier = Modifier .padding(10.dp)) {
Icon(
    painter = painterResource(id = R.drawable.filter),
    contentDescription = "",

)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.searchFilmsByKeyword(searchText)
                hideKeyboard()
            }


            )
        )


        SearchResultDisplay(searchData,navController)
    }
}

fun hideKeyboard() {
}

@Composable
fun FilterDialog(show: Boolean, onHide: () -> Unit, onApplyFilters: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = onHide,
            title = { Text("Apply Filters") },
            confirmButton = {
                Button(onClick = {
                    onApplyFilters()
                    onHide()
                }) {
                    Text("Apply")
                }
            },
            dismissButton = {
                Button(onClick = onHide) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun SearchResultDisplay(searchData: SearchData? , navController: NavController) {
    searchData?.films?.let { films ->
        if (films.isNotEmpty()) {
            LazyColumn {
                items(films) { film ->
                    SearchResultItem(film, navController)
                }
            }
        } else {
            Text("К сожалению , по вашему запросу ничего не найдено.", Modifier.padding(16.dp) , color = Color.Black)
        }
    } ?: Text("Start searching by typing a film name.", Modifier.padding(16.dp))
}
@Composable
fun SearchResultItem(film: Film, navController: NavController) {
    println("$film")
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)
        .clickable {
            navController.navigate("FilmPage/${film.filmId}")
        }
    ) {
        Box(modifier = Modifier
            .background(color = Color.White),
            contentAlignment = Alignment.TopEnd) {
            Row(modifier = Modifier .fillMaxWidth()) {
                Image(
                    painter = rememberAsyncImagePainter(film.posterUrlPreview),
                    contentDescription = null,
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(film.nameRu, style = MaterialTheme.typography.bodyLarge , color = Color.Black )
                    Text(film.genres.joinToString(", ") { it.genre }, style = MaterialTheme.typography.bodySmall , color = Color.Black  )
                }
            }


        }
    }
}


