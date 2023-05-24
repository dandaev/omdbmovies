package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private var movieList: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installRecycleView(movieList)
        binding.movieSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(text: String?): Boolean {
                searchByTitle(text).start()
                return true
            }

        })
    }

    private fun searchByTitle(text: String?): Thread {
        return Thread {
            val url = URL("https://www.omdbapi.com/?apikey=d7fd1eeb&s=$text&page=1")
            println(url)
            val connection = url.openConnection() as HttpsURLConnection

            if (connection.responseCode == 200){
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, SearchRequest::class.java)
                if (request != null){
                    println("Total: "+request.totalResults)
                    runOnUiThread {
                        kotlin.run {
                            installRecycleView(request.search)
                        }
                    }
                }
                else{
                    println("NULL")
                }
                inputStreamReader.close()
                inputSystem.close()
            }
            else{
                println("PROBLEM")
                installRecycleView(emptyList())
            }

        }
    }

    private fun installRecycleView(movies: List<Movie>?) {
        if (movies == null || movies.isEmpty()){
            binding.movieSearchRecycleView.visibility = View.GONE
            binding.warningText.visibility = View.VISIBLE
        }
        else{
            binding.movieSearchRecycleView.visibility = View.VISIBLE
            binding.warningText.visibility = View.GONE
            binding.movieSearchRecycleView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = MovieSearchAdapter(movies)
            }
        }
    }

}