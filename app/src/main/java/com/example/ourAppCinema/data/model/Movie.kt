package com.example.ourAppCinema.data.model

data class KinopoiskResponse(
    val films: List<Movie>
)

data class Movie(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val year: String,
    val genres: List<Genre>,
    val rating: String?,
    val posterUrlPreview: String,
    val countries: List<Country>,
    val description: String,
    val shortDescription: String?,
    val editorAnnotation: String?,
)

data class Genre(
    val genre: String
)
data class Country(
    val country: String
)
data class MovieDetails(
    val kinopoiskId: Int,
    val imdbId: String?,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: Int,
    val filmLength: Int?,
    val description: String,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val lastSync: String?,
    val genres: List<Genre>,
    val countries: List<Country>
)
