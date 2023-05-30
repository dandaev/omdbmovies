package com.example.movies.presentation

sealed class Screen(val route: String){
    object MovieSearchScreen: Screen("movie_search_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}
