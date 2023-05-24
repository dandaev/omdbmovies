package com.example.movies.data.remote

import com.example.movies.data.remote.dto.MovieDetailDto
import com.example.movies.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OmdbMovieApi {

    @GET("&s={title}")
    suspend fun getMoviesByTitle(@Path("title") title: String): MovieListDto

    @GET("&s={imdbId}")
    suspend fun getMovieById(@Path("imdbId") imdbId: String): MovieDetailDto
}