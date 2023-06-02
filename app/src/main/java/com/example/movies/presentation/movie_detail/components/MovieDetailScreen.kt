package com.example.movies.presentation.movie_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.domain.model.MovieDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    state: MovieDetailState
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    state.movie?.let { movie ->
        BackdropScaffold(
            scaffoldState = scaffoldState,
            gesturesEnabled = true,
            appBar = { },
            backLayerContent = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.movie_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .verticalScroll(scrollState)
                        .clickable {
                            if (scaffoldState.isConcealed) {
                                scope.launch {
                                    scaffoldState.reveal()
                                }
                            }
                        },
                )
            },
            frontLayerShape = MaterialTheme.shapes.large,
            frontLayerContent = {
                FrontLayerScreen(movie)
            }
        ) {
            if (scrollState.isScrollInProgress) {
                scope.launch {
                    scaffoldState.conceal()
                }
            }
        }
    }
}


@Composable
fun FrontLayerScreen(
    movie: MovieDetail
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = movie.title + "(${movie.year})",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = movie.plot,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Actors",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = movie.actors,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}


