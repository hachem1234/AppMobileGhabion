package com.itroad.movies.ui.movies

import com.itroad.movies.data.models.movies.Result


interface ListFormuleClickListener {
    fun onItemClick(position: Int,data: Result)
}