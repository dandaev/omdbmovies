package com.example.movies.presentation.movie_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movies.common.shadow
import com.example.movies.domain.model.MovieDetail
import com.example.movies.presentation.ui.theme.LightBlue10

@ExperimentalMaterial3Api
@Composable
fun MovieDetailScreen(
    state: MovieDetailState
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.movie?.let { movie ->
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentAlignment = Alignment.Center
                ) {
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
                            .fillMaxSize(),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            color = LightBlue10,
                            offsetX = 0.dp,
                            offsetY = -(30).dp,
                            blurRadius = 30.dp
                        )
                        .padding(20.dp)

                ) {
                    HeaderItem(movie = movie)
                    Spacer(modifier = Modifier.height(16.dp))
                    ParticipantItem(movie = movie)
                    Spacer(modifier = Modifier.height(16.dp))
                    DescItem(desc = movie.plot)
                }

            }

        }

    }

}

@Composable
fun HeaderItem(
    movie: MovieDetail
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.year + " (${movie.country})",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.genre,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp)
        ) {
            Text(
                text = movie.imdbRating,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ParticipantItem(
    movie: MovieDetail
) {
    Text(
        text = "Director ${movie.director}",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Stars ${movie.actors}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DescItem(
    desc: String
) {
    Text(
        text = "Introduction",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = desc,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun Preview() {
    val movieDetail = MovieDetail(
        "",
        "",
        "United States",
        "",
        "Action, Adventure, Fantasy",
        "",
        "",
        "",
        "",
        "",
        "Harry Potter",
        "",
        "2005",
        "",
        "7.7",
        ""
    )
    HeaderItem(movie = movieDetail)
}

