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
    val description: String,
    val filmId: Int,
    val rating: String?,
    val posterUrlPreview: String,
    val genres: List<Genre>,
    val countries: List<Country>,
    val shortDescription: String?,
    val nameRu: String,
    val nameEn: String,
    val year: String,
    val editorAnnotation: String?

)
data class MovieDetailsResponse (
    val details : List<MovieDetails>
)