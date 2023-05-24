package com.example.movies.domain.use_case.get_movies
import com.example.movies.common.Resource
import com.example.movies.data.remote.dto.toMovieList
import com.example.movies.domain.model.MovieList
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesByTitleUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(title: String): Flow<Resource<MovieList>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMoviesByTitle(title).toMovieList()
            emit(Resource.Success(movieList))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Http Error occured"))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        }
    }
}