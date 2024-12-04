package com.example.ourAppCinema.presentation.Navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.ourAppCinema.data.model.Images
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel

@Composable
fun GallerySection(images: Images?, navController: NavController) {
    println("gallery section")

        Column(modifier = Modifier.fillMaxSize()
            .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                println("In the row")
                Text("Gallery", style = MaterialTheme.typography.bodyLarge , color = Color.Black )
                TextButton(onClick = { navController.navigate("galleryScreen") }) {
                    Text("All")
                }
            }
            Column(

                modifier = Modifier.padding(16.dp)
                    .fillMaxSize()
            ){
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
            ) {
                println("In the LazyRow")
                    items(images?.items?.take(6) ?: emptyList()) { item ->
                        ImageThumbnail(item.previewUrl)
                    }
                }
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryAll(navController: NavHostController , filmId : Int) {
    val viewModels: MoviesViewModel = viewModel()
    val images by viewModels.imageGallery.collectAsState()
    LaunchedEffect(filmId) {
        viewModels.fetchImages(filmId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gallery") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(images?.items?.take(20) ?: emptyList()) { item ->
                ImageThumbnail(url = item.previewUrl)
                println(item.previewUrl)
                println(item.imageUrl)
            }
        }
    }
}
@Composable
fun ImageThumbnail(url: String) {
    val painter = rememberAsyncImagePainter(
        model = url,
        onError = { println("Error loading image: $url") },
        onSuccess = { println("Loaded image successfully: $url") }

    )

    Box(

        modifier = Modifier
            .height(150.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        println(url)
        Image(
            painter = painter,
            contentDescription = "Gallery Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}