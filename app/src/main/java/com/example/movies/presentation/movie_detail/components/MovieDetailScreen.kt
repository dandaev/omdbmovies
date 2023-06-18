package com.example.movies.presentation.movie_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movies.common.drawLeftLine
import com.example.movies.common.shadow
import com.example.movies.domain.model.MovieDetail
import com.example.movies.presentation.ui.theme.*

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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            )
            {
                item {
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
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                color = LightBlue10,
                                offsetX = -(30).dp,
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

}

@Composable
fun HeaderItem(
    movie: MovieDetail
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(6f)
        ) {
            ResizebleTitleText(text = movie.title)
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
                .weight(2f)
                .padding(10.dp)
        ) {
            RatingCircleItem(
                rating = movie.imdbRating,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(70.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                LightBlue40,
                                LightBlue10
                            )
                        )
                    )
                    .drawLeftLine(
                        LightBlue10,
                        10.dp
                    )
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
        text = "Stars: ${movie.actors}",
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

@Composable
fun ResizebleTitleText(
    text: String
) {
    var fontSize = 32
    var fontHeight = 40.0

    if (text.length > 26) {
        fontSize = 20
        fontHeight = 20.0
    }

    Text(
        text = text,
        style = TextStyle(
            fontFamily = montserratExtraBold,
            fontSize = fontSize.sp,
            lineHeight = fontHeight.sp,
        ),
    )
}

@Composable
fun RatingCircleItem(
    rating: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = rating,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(Alignment.Center)
        )
    }
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
//    HeaderItem(movie = movieDetail)
    RatingCircleItem(rating = "7.7", Modifier)
}


