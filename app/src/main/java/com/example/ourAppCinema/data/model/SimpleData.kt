package com.example.ourAppCinema.data.model

data class SimpleFilm(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String?,
    val posterUrlPreview: String
)

data class SearchedData(
    val films: List<SimpleFilm>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)