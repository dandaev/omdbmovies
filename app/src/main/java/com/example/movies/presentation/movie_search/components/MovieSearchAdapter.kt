package com.example.movies.presentation.movie_search.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.common.Constants
import com.example.movies.databinding.SearchItemMovieBinding
import com.example.movies.domain.model.Movie

class MovieSearchAdapter(
    private val movies: List<Movie>,
    private val clickListener: MovieClickListener
) :
    RecyclerView.Adapter<MovieSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = SearchItemMovieBinding.inflate(from, parent, false)
        return MovieSearchViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }
}