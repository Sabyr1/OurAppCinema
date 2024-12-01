package com.example.ourAppCinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.ourAppCinema.data.api.KinopoiskApiService
import com.example.ourAppCinema.data.model.Movie
import com.example.ourAppCinema.data.api.RetrofitClient
import com.example.ourAppCinema.data.api.RetrofitClient.instance
import com.example.ourAppCinema.data.model.Actor
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.presentation.FilmDetailState
import retrofit2.Retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MoviessViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetails?>(null)
    val movieDetail: StateFlow<MovieDetails?> = _movieDetail

    fun fetchMovieDetails(filmId: Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getMovieDetails(filmId).awaitResponse()

                if (response.isSuccessful && response.body() != null) {
                    _movieDetail.value = response.body()
                } else {
                    println("errorr ${response.message()}" )
                }

        }

    }
}



class MoviesViewModel : ViewModel() {
    private val _getOscarState = MutableStateFlow<List<Movie>>(emptyList())
    val getOscarState: StateFlow<List<Movie>> = _getOscarState

    private val _movieDetailState = MutableStateFlow<List<MovieDetails>>(emptyList())
    val movieDetailState: StateFlow<List<MovieDetails>> = _movieDetailState

    private val _movieDetail = MutableStateFlow<MovieDetails?>(null)
    val movieDetail: StateFlow<MovieDetails?> = _movieDetail

    private val _popularState = MutableStateFlow<List<Movie>>(emptyList())
    val popularState: StateFlow<List<Movie>> = _popularState

    private val _popularState10 = MutableStateFlow<List<Movie>>(emptyList())
    val popularState10: StateFlow<List<Movie>> = _popularState10

    private val _top250State = MutableStateFlow<List<Movie>>(emptyList())
    val top250State: StateFlow<List<Movie>> = _top250State

    private val _top250State10 = MutableStateFlow<List<Movie>>(emptyList())
    val top250State10: StateFlow<List<Movie>> = _top250State10

    private val _actorsState = MutableStateFlow<Actor>(Actor())
    val actorsState: StateFlow<Actor> = _actorsState

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            fetchData(::fetchPopular, _popularState)
            fetchData(::fetchPopular10, _popularState10)
            fetchData(::fetchTop250, _top250State)
            fetchData(::fetchTop25010, _top250State10)
        }
    }

    fun fetchMovieDetails(filmId: Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getMovieDetails(filmId).awaitResponse()
            if (response.isSuccessful && response.body() != null) {
                _movieDetail.value = response.body()
            } else {
                _movieDetail.value = null
            }
        }
    }

    private suspend fun fetchData(
        fetchFunction: suspend () -> Unit,
        stateFlow: MutableStateFlow<List<Movie>>
    ) {
        try {
            fetchFunction()
        } catch (e: Exception) {
            e.printStackTrace() // Log the error
        }
    }

    private suspend fun fetchPopular10() {
        val response = RetrofitClient.instance.getTopMovies().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.films?.let { _popularState10.value = it.take(10) }
        }
    }

    private suspend fun fetchPopular() {
        val response = RetrofitClient.instance.getTopMovies().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.films?.let { _popularState.value = it.take(15) }
        }
    }

    private suspend fun fetchGetOscarWinMovies() {
        val response = RetrofitClient.instance.getOskarWinMovies().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.films?.let { _getOscarState.value = it.take(10) }
        }
    }

    private suspend fun fetchTop250() {
        val response = RetrofitClient.instance.getTop250Movies().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.films?.let { _top250State.value = it.take(15) }
        }
    }

    private suspend fun fetchTop25010() {
        val response = RetrofitClient.instance.getTop250Movies().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.films?.let { _top250State10.value = it.take(10) }
        }
    }
    fun fetchActors(filmId: Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getActor(filmId).awaitResponse()
            if (response.isSuccessful) {
                _actorsState.value = response.body() ?: Actor()
            } else {

            }
        }
    }
}


