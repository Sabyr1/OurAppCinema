package com.example.ourAppCinema.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.ourAppCinema.data.model.Movie
import com.example.ourAppCinema.ui.theme.blue
import com.example.ourAppCinema.ui.theme.graphik_bold
import com.example.ourAppCinema.ui.theme.graphik_medium

@Composable
fun MovieCard(movie: Movie, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp)
            .height(330.dp)

            .clickable {
                navController.navigate("FilmPage/${movie.filmId}")
            }
    ) {
        Column(
            modifier = Modifier
                .height(330.dp)
                .width(160.dp)
                .background(
                    color = Color.White
                ),
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd

            ){
                Image(
                    painter = rememberImagePainter(movie.posterUrlPreview),
                    modifier = Modifier.fillMaxWidth().height(237.6.dp).width(120.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 6.dp, end = 8.dp)
                ) {
                    Text(
                        text = movie.rating ?: "N/A",
                        modifier = Modifier
                            .background(color = blue,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .height(19.82.dp)
                            .width(34.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = W400
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column {
                Text(
                    text = movie.nameRu,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = W800
                )
            }
            Column(){
                Text(
                    text = movie.genres.joinToString(", ") { it.genre },
                    fontSize = 8.sp,
                    color = Color.Black,
                    fontWeight = W400
                )
            }
        }
    }
}

@Composable
fun Oskars (movie: Movie, navController: NavController){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(330.dp)
            .clickable {
                navController.navigate("FilmPage/${movie.filmId}")
            }
    ) {
        Column(
            modifier = Modifier
                .height(330.dp)
                .width(150.dp)
                .background(
                    color = Color.White
                ),
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd

            ){
                Image(
                    painter = rememberImagePainter(movie.posterUrlPreview),
                    modifier = Modifier.fillMaxWidth().height(237.6.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column {
                Text(
                    text = movie.nameRu,
                    fontFamily = graphik_bold,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = W800
                )
            }
            Column(){
                Text(
                    text = movie.genres.joinToString(", ") { it.genre },
                    fontSize = 12.sp,
                    fontFamily = graphik_medium,
                    color = Color.Black,
                    fontWeight = W400
                )
            }
        }
    }
}


/*
Image(
painter = rememberImagePainter(movie.posterUrlPreview),
contentDescription = movie.nameRu,
contentScale = ContentScale.Crop,
modifier = Modifier.height(180.dp)
)
Spacer(modifier = Modifier.height(8.dp))
Text(
text = movie.nameRu,
fontWeight = FontWeight.Bold,
fontSize = 14.sp
)
Text(
text = movie.genres.joinToString(", ") { it.genre },
fontFamily = graphik,
fontWeight = FontWeight.W500,
fontSize = 12.sp
)
Text(
text = movie.rating ?: "N/A",
fontSize = 12.sp,
fontWeight = FontWeight.SemiBold
)
 */
