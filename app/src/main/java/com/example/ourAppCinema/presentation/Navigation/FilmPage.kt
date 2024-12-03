package com.example.ourAppCinema.FilmPage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ourAppCinema.R
import com.example.ourAppCinema.data.api.RetrofitClient
import com.example.ourAppCinema.data.model.Actor
import com.example.ourAppCinema.data.model.ActorItem
import com.example.ourAppCinema.data.model.Images
//import com.example.ourAppCinema.api.Actor
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.presentation.Navigation.ActorsSection
import com.example.ourAppCinema.presentation.Navigation.GallerySection
import com.example.ourAppCinema.ui.theme.graphik_medium
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel


@Composable
fun FilmPage(navController: NavHostController, filmId: Int ) {
    val viewModels: MoviesViewModel = viewModel()
    val movie by viewModels.movieDetail.collectAsState()
    val actor by viewModels.actorsState.collectAsState()
    val image by viewModels.imageGallery.collectAsState()

    LaunchedEffect(filmId) {
        println("film page 3")

        viewModels.fetchMovieDetails(filmId)
        viewModels.fetchActors(filmId)
        viewModels.fetchImages(filmId)
    }

    if (movie != null) {
        FilmPageDetail(navController = navController, movie = movie!! , actors = actor , images = image)


    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

    @Composable
    fun FilmPageDetail(movie: MovieDetails, navController: NavController , actors : Actor , images: Images?) {

        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier ) {
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.BottomCenter,

                    ) {
                    Image(
                        painter = rememberAsyncImagePainter(movie.posterUrlPreview),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            }
                    )

                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(40.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = movie.nameEn ?: "",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${movie.ratingKinopoisk ?: "0.0"} • ${movie.nameRu} • ${movie.year}",
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = movie.countries.joinToString(", ") { it.country },
                            fontSize = 12.sp,
                            fontFamily = graphik_medium,
                            color = Color.Gray,
                            fontWeight = W400
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = movie.genres.joinToString(", ") { it.genre },
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                    }
                }
                Column (modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = movie.description,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                }
                Column(modifier = Modifier .height(250.dp) .fillMaxWidth()) {
                    GallerySection(images, navController)
                }
                Column(modifier = Modifier .fillMaxSize()){
                    ActorsSection(actors , navController)
                }
                println("Before Gallery Section")

            }
        }
    }






