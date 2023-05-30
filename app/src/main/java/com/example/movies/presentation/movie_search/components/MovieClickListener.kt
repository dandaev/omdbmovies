package com.example.movies.presentation.movie_search.components

import com.example.movies.domain.model.Movie

interface MovieClickListener {
    fun onClick(movie: Movie)
}