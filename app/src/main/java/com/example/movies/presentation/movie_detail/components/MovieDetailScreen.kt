package com.example.movies.presentation.movie_detail

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
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
import com.example.movies.common.vertical
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
                        contentAlignment = Alignment.TopEnd
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

                        LeftBlurRectangle(
                            modifier = Modifier
                                .width(90.dp)
                                .fillMaxHeight()
                                .align(Alignment.TopStart)
                                .drawBehind {
                                    drawIntoCanvas { canvas ->
                                        val paint = Paint()
                                        val frameworkPaint = paint.asFrameworkPaint()
                                        frameworkPaint.maskFilter =
                                            (BlurMaskFilter(20f, BlurMaskFilter.Blur.NORMAL))
                                        frameworkPaint.color = HalfTransparentBlue.toArgb()
                                        canvas.drawRect(
                                            left = 0.dp.toPx(),
                                            top = -(16).dp.toPx(),
                                            right = size.width + 10.dp.toPx(),
                                            bottom = size.height + 16.dp.toPx(),
                                            paint = paint
                                        )
                                    }
                                }
                                .background(Color.Transparent), movie.runtime, movie.country, movie.year)

                        SaveIconButton(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 25.dp, end = 25.dp)
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
                                Color.Transparent,
                                Color.Transparent,
                            )
                        )
                    )
                    .drawLeftLine()
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

@Composable
fun SaveIconButton(
    modifier: Modifier
) {
    var isPressed by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        Box(
            Modifier
                .size(50.dp)
                .clickable {
                    isPressed = !isPressed
                }
                .clip(MaterialTheme.shapes.small)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            LightBlue90,
                            Color.Transparent,
                            Color.Transparent,
                        )
                    )
                )
                .drawLeftLine(),
            contentAlignment = Alignment.Center,

        ) {

            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = if (isPressed) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun LeftBlurRectangle(
    modifier: Modifier,
    time: String,
    country: String,
    year: String
) {
    Box(
        modifier = modifier,
    )
    {
        Column(
            Modifier
                .fillMaxHeight().align(Alignment.BottomCenter)
        ) {

            Spacer(modifier = Modifier.height(185.dp))
//            Text(
//                text = year + " (${country})",
//                Modifier.vertical().rotate(-90f),
//                style = MaterialTheme.typography.titleMedium,
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Icon(
//                modifier = Modifier.size(25.dp).rotate(-90f),
//                imageVector = Icons.Outlined.CalendarMonth,
//                contentDescription = ""
//            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = time,
                Modifier
                    .vertical().rotate(-90f),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Icon(
                modifier = Modifier.size(25.dp).rotate(-90f),
                imageVector = Icons.Outlined.AccessTime,
                contentDescription = ""
            )
        }
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


