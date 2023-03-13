package com.itroad.movies.data.models.movies

data class ResponseMovie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)