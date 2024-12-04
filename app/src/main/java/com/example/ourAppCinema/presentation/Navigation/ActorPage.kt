package com.example.ourAppCinema.presentation.Navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ourAppCinema.data.model.Actor
import com.example.ourAppCinema.data.model.ActorItem

@Composable
fun ActorsSection(actors: Actor, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "В фильме снимались",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Все ${actors.size}",
                    color = Color.Blue,
                    modifier = Modifier.padding(end = 8.dp)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(16.dp)
                .fillMaxSize()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(actors.take(8)) { actor ->
                        ActorItem(actor = actor ,navController)
                    }
                }
            }

        }
    }
}


@Composable
fun ActorItem(actor: ActorItem,navController: NavController) {
    Card( modifier = Modifier .background(color = Color.White)
        .clickable {
        navController.navigate("actorDetails/${actor.staffId}")

        }  ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(100.dp)
                .background(color = Color.White)
        ) {
            Image(
                painter = rememberAsyncImagePainter(actor.posterUrl),
                contentDescription = "Actor image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = actor.nameRu,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
            )
        }
    }
}