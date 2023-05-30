package com.example.movies.presentation.movie_search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.common.Constants
import com.example.movies.databinding.FragmentMovieSearchBinding
import com.example.movies.domain.model.Movie
import com.example.movies.presentation.movie_search.components.MovieClickListener
import com.example.movies.presentation.movie_search.components.MovieSearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieSearchFragment : Fragment(), MovieClickListener {

    private lateinit var binding: FragmentMovieSearchBinding
    private val viewModel: MovieSearchViewModel by viewModels()

    private lateinit var thisFragment: View

    companion object {
        fun newInstance(): MovieSearchFragment {
            return MovieSearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisFragment = view
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                binding.movieSearchRecycleView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = MovieSearchAdapter(it.movies, this@MovieSearchFragment)
                }
            }
        }



        binding.movieSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.getMoviesByTitle(text.toString())
                return true
            }
        })
    }

    override fun onClick(movie: Movie) {
        val args = bundleOf(Constants.PARAM_MOVIE_ID to movie.imdbID)
        val direction =
            MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment()
        findNavController().safeNavigate(direction, args)
    }

    fun NavController.safeNavigate(direction: NavDirections, args: Bundle) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction.actionId, args)
        }
    }
}