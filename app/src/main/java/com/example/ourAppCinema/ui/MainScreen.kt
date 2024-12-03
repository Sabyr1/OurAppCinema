package com.example.ourAppCinema.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ourAppCinema.FilmPage.FilmPage
import com.example.ourAppCinema.FilmPage.FilmPageDetail
import com.example.ourAppCinema.presentation.Navigation.HomePage
import com.example.ourAppCinema.presentation.Navigation.ProfilePage
import com.example.ourAppCinema.presentation.Navigation.SearchingPage
import com.example.ourAppCinema.presentation.Navigation.AllPopGrid
import com.example.ourAppCinema.presentation.Navigation.All250Grid
import com.example.ourAppCinema.R
import com.example.ourAppCinema.data.model.Movie
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.presentation.Navigation.ActorDetailsScreen
import com.example.ourAppCinema.presentation.Navigation.BottomNavigation
import com.example.ourAppCinema.presentation.Navigation.GalleryAll
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier,viewModel: MoviesViewModel = viewModel()) {
    val navController = rememberNavController()

    val popular by viewModel.popularState10.collectAsState()
    val oscar by viewModel.getOscarState.collectAsState()
    val top250 by viewModel.top250State10.collectAsState()
    val movies by viewModel.movieDetailState.collectAsState()
    val images by viewModel.imageGallery.collectAsState()

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
                SearchingPage(viewModel,navController )
            }
            composable("profile") {
                ProfilePage()
            }
            composable("actorDetails/{actorId}", arguments = listOf(navArgument("actorId") { type = NavType.IntType })) { backStackEntry ->
                val actorId = backStackEntry.arguments?.getInt("actorId") ?: 0
                ActorDetailsScreen(actorId, navController)
            }

            composable(
                route = "FilmPage/{filmId}",
                arguments = listOf(
                    navArgument("filmId") {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) {  backStackEntry ->

                val filmId = backStackEntry.arguments?.getInt("filmId")
                if (filmId == null) {

                    return@composable
                }
                FilmPage(navController = navController, filmId = filmId)
            }

            }

        }

}


data class  NavItem (
    val icon : Painter
)



