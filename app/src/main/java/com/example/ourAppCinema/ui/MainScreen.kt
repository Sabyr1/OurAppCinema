package com.example.ourAppCinema.ui


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ourAppCinema.FilmPage.FilmPage
import com.example.ourAppCinema.presentation.Navigation.HomePage
import com.example.ourAppCinema.presentation.Navigation.ProfilePage
import com.example.ourAppCinema.presentation.Navigation.SearchingPage
import com.example.ourAppCinema.presentation.Navigation.AllPopGrid
import com.example.ourAppCinema.presentation.Navigation.All250Grid
import com.example.ourAppCinema.R
import com.example.ourAppCinema.data.model.Movie
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.presentation.Navigation.BottomNavigation
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier,viewModel: MoviesViewModel = viewModel()) {
    val navController = rememberNavController()

    val popular by viewModel.popularState10.collectAsState()
    val oscar by viewModel.getOscarState.collectAsState()
    val top250 by viewModel.top250State10.collectAsState()
    val movies by viewModel.movieDetailState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {

                HomePage(navController = navController)
            }
            composable("list_popular") {

                AllPopGrid(navController = navController)
            }
            composable("list_top250")
            {
                All250Grid(navController = navController)
            }
            composable("searching") {
                SearchingPage()
            }
            composable("profile") {
                ProfilePage()
            }
            composable("FilmPage/{filmId}")
            {

                }
            }
        }
    }


data class NavItem (
    val icon : Painter
)

private val allMovies = mutableListOf<Movie>()

fun findMovieById(filmId: Int, movies: List<MovieDetails>): MovieDetails? {
    return movies.firstOrNull { it.filmId == filmId }


}






//fun findMovieById(filmId: String?, popular: List<Movie>, premieres: List<Movie>, top250: List<Movie>): Movie? {
//    return popular.firstOrNull { it.filmId.toString() == filmId }
//        ?: premieres.firstOrNull { it.filmId.toString() == filmId }
//        ?: top250.firstOrNull { it.filmId.toString() == filmId }
//}



//val filmId = backStackEntry.arguments?.getString("filmId")
//val movie = findMovieById(filmId, popular, premieres, top250)
//if (movie != null) {
//    FilmPage(movie = movie, navController = navController)
//} else {
//    Text(
//        text = "Error: Movie not found"
//    )