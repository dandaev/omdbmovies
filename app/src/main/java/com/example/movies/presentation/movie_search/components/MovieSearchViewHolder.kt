package com.example.movies.presentation.movie_search.components

import android.content.Context
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.SearchItemMovieBinding
import com.example.movies.domain.model.Movie
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

class MovieSearchViewHolder(
    private val searchItemMovieBinding: SearchItemMovieBinding,
    private val clickListener: MovieClickListener
) : RecyclerView.ViewHolder(searchItemMovieBinding.root) {
    fun bindMovie(movie: Movie) {
        searchItemMovieBinding.movieLogo.setContent {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        searchItemMovieBinding.title.text = movie.title
        searchItemMovieBinding.year.text = movie.year
        searchItemMovieBinding.type.text = movie.type
        searchItemMovieBinding.cardView.setOnClickListener {
            clickListener.onClick(movie)
        }
    }


}