package com.example.ourAppCinema.data.api

import com.example.ourAppCinema.data.model.Actor
import com.example.ourAppCinema.data.model.KinopoiskResponse
import com.example.ourAppCinema.data.model.MovieDetails
import com.example.ourAppCinema.data.model.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "55a29254-7f72-4c93-9d0c-e4fdd7b59094"
//23b0f1f8-91a7-46cf-a70c-ac7bb8b140e0
interface KinopoiskApiService {


    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/top")
    fun getTopMovies(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int = 2
    ): Call<KinopoiskResponse>


    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/premieres")
    fun getOskarWinMovies(
        @Query("month") month: String = "JULY",
        @Query("year") year: Int = 2018
    ): Call<KinopoiskResponse>

    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/top")
    fun getTop250Movies(
        @Query("type") type: String = "TOP_250_BEST_FILMS",
        @Query("page") page: Int = 2
    ): Call<KinopoiskResponse>

    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/{id}")
    fun getMovieDetails(
        @Path ("id") id: Int = 430
    ): Call<MovieDetails>
    @Headers("X-API-KEY: $API_KEY")
    @GET("v1/staff")
    fun getActor(
        @Query("filmId") filmId: Int
    ): Call<Actor>


}
