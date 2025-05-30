package com.example.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieList(items: List<TmdbItem>) {
    LazyColumn {
        items(items) { item ->
            Row(modifier = Modifier.padding(8.dp)) {
                val imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}"
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = item.title ?: item.name ?: "Unknown", fontSize = 20.sp)
                    Text(text = item.overview, maxLines = 3, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: TmdbViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else if (viewModel.errorMessage != null) {
        Text("Error: ${viewModel.errorMessage}")
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Top Rated Movies", fontSize = 24.sp)
            MovieList(viewModel.movies)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Top Rated TV Shows", fontSize = 24.sp)
            MovieList(viewModel.tvShows)
        }
    }
}
