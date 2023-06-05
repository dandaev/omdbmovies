package com.example.movies.presentation.movie_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movies.domain.model.MovieDetail

@ExperimentalMaterial3Api
@Composable
fun MovieDetailScreen(
    state: MovieDetailState
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    state.movie?.let { movie ->
        BottomSheetScaffold(
            sheetContent = {
                FrontLayerScreen(movie = movie)
            }
        )
        {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.poster)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .verticalScroll(scrollState)
                        .clickable {},
                )
            }

        }
    }
}




@Composable
fun FrontLayerScreen(
    movie: MovieDetail
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onSurfaceVariant)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = movie.title + "(${movie.year})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = movie.plot,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Actors",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = movie.actors,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


