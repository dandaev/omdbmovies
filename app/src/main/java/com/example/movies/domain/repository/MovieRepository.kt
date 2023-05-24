package com.example.movies.domain.repository

import com.example.movies.data.remote.dto.MovieDetailDto
import com.example.movies.data.remote.dto.MovieListDto

interface MovieRepository {

    suspend fun getMoviesByTitle(title: String): MovieListDto

    suspend fun getMovieById(omdbId: String): MovieDetailDto
}