package com.example.ourAppCinema.data.model.search

data class SearchData(
    val films: List<Films>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)