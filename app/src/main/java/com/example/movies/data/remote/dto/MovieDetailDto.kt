package com.example.movies.data.remote.dto

import com.example.movies.domain.model.MovieDetail

data class MovieDetailDto(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<RatingDto>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)

fun MovieDetailDto.toMovieDeatail(): MovieDetail {
    return MovieDetail(
        actors = Actors,
        awards = Awards,
        country = Country,
        director = Director,
        language = Language,
        genre = Genre,
        plot = Plot,
        poster = Poster,
        released = Released,
        runtime = Runtime,
        title = Title,
        type = Type,
        year = Year,
        imdbID = imdbID,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes
    )
}