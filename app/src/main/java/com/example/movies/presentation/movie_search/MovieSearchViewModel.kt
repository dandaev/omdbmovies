package com.example.movies.presentation.movie_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Resource
import com.example.movies.domain.use_case.get_movies.GetMoviesByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMoviesByTitleUseCase: GetMoviesByTitleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    fun getMoviesByTitle(title: String) {
        getMoviesByTitleUseCase(title).onEach{ result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data?.movieList ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}