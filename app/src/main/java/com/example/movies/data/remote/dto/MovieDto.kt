package com.example.movies.data.remote.dto

import com.example.movies.domain.model.Movie

data class MovieDto(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)

fun MovieDto.toMovie(): Movie{
    return Movie(
        poster = Poster,
        title = Title,
        type = Type,
        year = Year,
        imdbID = imdbID
    )
}