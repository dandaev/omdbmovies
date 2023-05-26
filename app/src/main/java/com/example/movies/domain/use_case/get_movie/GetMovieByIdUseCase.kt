package com.example.movies.domain.use_case.get_movie

import com.example.movies.common.Resource
import com.example.movies.data.remote.dto.toMovieDeatail
import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(id: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovieById(id).toMovieDeatail()
            emit(Resource.Success(movie))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Http Error occured"))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        }
    }
}