package com.example.movies.data.remote.dto

import com.example.movies.domain.model.MovieList
import com.google.gson.annotations.SerializedName

data class MovieListDto(
    val Response: String,
    @SerializedName("Search")
    val movieList: List<MovieDto>?,
    val totalResults: String?
)

fun MovieListDto?.toMovieList(): MovieList?{
    if(this == null || this.movieList == null) return null
    return MovieList(
        movieList = movieList.map { it.toMovie() },
        totalResults = totalResults!!.toIntOrNull() ?: 1
    )
}