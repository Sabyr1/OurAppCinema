package com.example.ourAppCinema.FilmPage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ourAppCinema.R
import com.example.ourAppCinema.data.api.RetrofitClient
//import com.example.ourAppCinema.api.Actor
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.ui.theme.graphik_medium
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun FilmPage(navController: NavController,
filmDetVM: MoviesViewModel = viewModel()
) {


    val movieDetailsState by filmDetVM.movieDetailState.collectAsState()

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 100.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ){
                        val movie = filmDetVM.movieDetailState
                    }
        }
    }
}
    @Composable
    fun FilmPageDetail(movie: MovieDetails, navController: NavController) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ) {
            Column {
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
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
                            text = movie.nameEn.uppercase() ,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${movie.rating ?: "0.0"} • ${movie.nameRu} • ${movie.year}",
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
                Column {
                    Text(
                        text = "${movie.editorAnnotation}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    )


                    Text(
                        text = "Описание: ${movie.description}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                }
            }
        }
    }
}





//@Composable
//fun ActorsSection(actors: List<Actor>?) {
//    if (actors.isNullOrEmpty()) {
//        Text(
//            text = "No actors available",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            color = Color.Black,
//            modifier = Modifier.padding(16.dp)
//        )
//    } else {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(
//                text = "Actors",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                items(actors) { actor ->
//                    ActorItem(actor = actor)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ActorItem(actor: Actor) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.width(100.dp)
//    ) {
//        Image(
//            painter = rememberImagePainter(actor.posterUrl),
//            contentDescription = "Actor image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(80.dp)
//                .background(Color.Gray)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = actor.name,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Normal,
//            color = Color.Black,
//            modifier = Modifier.fillMaxWidth(),
//            maxLines = 1,
//        )
//    }
//}
