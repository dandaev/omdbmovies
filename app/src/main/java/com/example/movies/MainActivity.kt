package com.example.movies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.presentation.movie_search.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installRecycleView()
        binding.movieSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.getMoviesByTitle(text.toString())
                installRecycleView()
                return true
            }
        })
    }

    private fun installRecycleView() {
        if (viewModel.state.value.movies.isEmpty()){
            binding.movieSearchRecycleView.visibility = View.GONE
            binding.warningText.visibility = View.VISIBLE
            binding.warningText.text = "Error"
        }else{
            println("Not error")
            binding.movieSearchRecycleView.visibility = View.VISIBLE
            binding.warningText.visibility = View.GONE
            binding.movieSearchRecycleView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = MovieSearchAdapter(viewModel.state.value.movies)
            }
        }
    }

}