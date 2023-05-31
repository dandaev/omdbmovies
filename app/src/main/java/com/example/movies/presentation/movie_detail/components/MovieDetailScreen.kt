package com.example.movies.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    BackdropScaffold(
        scaffoldState = scaffoldState,
        gesturesEnabled = false,
        appBar = { },
        backLayerContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(Color.Blue)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.movie?.poster)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.movie_default),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Black)
                )
            }
        },
        frontLayerElevation = 30.dp,
        frontLayerShape = MaterialTheme.shapes.large,
        frontLayerContent = {
            state.movie?.let { movie ->
                FrontLayerScreen(movie)
            }

        }
    ){
        if (scrollState.isScrollInProgress){
            scope.launch {
                scaffoldState.conceal()
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


