package com.example.movies

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.SearchItemMovieBinding
import com.squareup.picasso.Picasso

class MovieSearchViewHolder(
    private val searchItemMovieBinding: SearchItemMovieBinding,
    private val context: Context
) : RecyclerView.ViewHolder(searchItemMovieBinding.root)
{
    fun bindMovie(movie: Movie)
    {
        Picasso.get().load(movie.poster).placeholder(ContextCompat.getDrawable(context, R.drawable.movie_default)!!).error(ContextCompat.getDrawable(context, R.drawable.movie_default)!!).into(searchItemMovieBinding.movieLogo)
        searchItemMovieBinding.title.text = movie.title
        searchItemMovieBinding.year.text = movie.year.toString()
    }
}