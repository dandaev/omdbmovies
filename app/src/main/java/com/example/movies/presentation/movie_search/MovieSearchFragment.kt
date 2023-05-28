package com.example.movies.presentation.movie_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentMovieSearchBinding
import com.example.movies.presentation.movie_search.components.MovieSearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieSearchFragment : Fragment() {

    private lateinit var binding: FragmentMovieSearchBinding
    private val viewModel: MovieSearchViewModel by viewModels()

    companion object{
        fun newInstance(): MovieSearchFragment{
            return MovieSearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieSearchBinding.inflate(inflater,container,false)
        val view: View = binding.root

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                binding.movieSearchRecycleView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = MovieSearchAdapter(it.movies)
                }
            }
        }

        binding.movieSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.getMoviesByTitle(text.toString())
                return true
            }
        })
        return view
    }
}