package com.itroad.movies.data.models

data class emploieurItem(
    val `data`: List<Data>?,
    val page: Int?=0,
    val per_page: Int?=0,
    val support: Support?,
    val total: Int?=0,
    val total_pages: Int?=0
)