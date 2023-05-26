package com.example.movies.domain.model

import com.example.movies.data.remote.dto.RatingDto

data class MovieDetail(
    val actors: String,
    val awards: String,
    val country: String,
    val director: String,
    val genre: String,
    val language: String,
    val plot: String,
    val poster: String,
    val released: String,
    val runtime: String,
    val title: String,
    val type: String,
    val year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)
