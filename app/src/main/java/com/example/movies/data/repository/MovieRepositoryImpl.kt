package com.example.movies.data.repository

import com.example.movies.common.Constants
import com.example.movies.data.remote.OmdbMovieApi
import com.example.movies.data.remote.dto.MovieDetailDto
import com.example.movies.data.remote.dto.MovieListDto
import com.example.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: OmdbMovieApi
) : MovieRepository {

    override suspend fun getMoviesByTitle(title: String): MovieListDto {
        return api.getMoviesByTitle(Constants.API_KEY,title)
    }

    override suspend fun getMovieById(omdbId: String): MovieDetailDto {
        return api.getMovieById(omdbId)
    }
}