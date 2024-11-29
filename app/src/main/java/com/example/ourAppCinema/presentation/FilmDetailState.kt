package com.example.ourAppCinema.presentation

import com.example.ourAppCinema.data.model.MovieDetails

data class FilmDetailState (
    var loading : Boolean = false,
    var movie : MovieDetails? = null,
    var error: String = "Error",
    var filmId: Int? =0
)
