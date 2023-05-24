package com.example.movies

import com.google.gson.annotations.SerializedName

class SearchRequest(
    @SerializedName("Search")
    var search: List<Movie>,
    var totalResults: String,
    @SerializedName("Response")
    var response: String
)