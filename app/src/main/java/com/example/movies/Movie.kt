package com.example.movies

import com.google.gson.annotations.SerializedName
import java.time.Year

class Movie (
    @SerializedName("Title")
    var title: String,
    @SerializedName("Year")
    var year: String,
    var imdbId: String,
    @SerializedName("Type")
    var type: String,
    @SerializedName("Poster")
    var poster: String
)