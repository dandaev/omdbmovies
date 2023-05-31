package com.example.movies.presentation.movie_search.components

import android.content.Context
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.databinding.SearchItemMovieBinding
import com.example.movies.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieSearchViewHolder(
    private val searchItemMovieBinding: SearchItemMovieBinding,
    private val context: Context,
    private val clickListener: MovieClickListener
) : RecyclerView.ViewHolder(searchItemMovieBinding.root)
{
    fun bindMovie(movie: Movie)
    {
        Picasso.get().load(movie.poster).placeholder(ContextCompat.getDrawable(context,
            R.drawable.movie_default
        )!!).error(ContextCompat.getDrawable(context, R.drawable.movie_default)!!).into(searchItemMovieBinding.movieLogo)
//        searchItemMovieBinding.movieLogo.setContent {TODO Realize it with Coil
//            AsyncImage(model = movie.poster, placeholder = painterResource(id = R.drawable.movie_default), contentDescription = null)
//        }
        searchItemMovieBinding.title.text = movie.title
        searchItemMovieBinding.year.text = movie.year
        searchItemMovieBinding.type.text = movie.type
        searchItemMovieBinding.cardView.setOnClickListener {
            clickListener.onClick(movie)
        }
    }


}