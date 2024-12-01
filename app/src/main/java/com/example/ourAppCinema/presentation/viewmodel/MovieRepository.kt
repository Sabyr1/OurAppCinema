package com.example.ourAppCinema.presentation.viewmodel

import com.example.ourAppCinema.data.api.KinopoiskApiService
import com.example.ourAppCinema.data.model.MovieDetails

class MovieRepository(private val apiService: KinopoiskApiService) {

//    suspend fun getMovieDetails(filmId: Int): MovieDetails? {
//        return try {
//            println(filmId)
//            apiService.getMovieDetails(filmId)
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
}