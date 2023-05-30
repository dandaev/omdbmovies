package com.example.movies.data.remote

import com.example.movies.common.Constants
import com.example.movies.data.remote.dto.MovieDetailDto
import com.example.movies.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbMovieApi {

    @GET("/")
    suspend fun getMoviesByTitle(@Query("apikey")apiKey: String, @Query("s") title: String): MovieListDto

    @GET("/")
    suspend fun getMovieById(@Query("apikey")apiKey: String, @Query("i") imdbId: String): MovieDetailDto
}