package com.example.movies.presentation.movie_detail

import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)