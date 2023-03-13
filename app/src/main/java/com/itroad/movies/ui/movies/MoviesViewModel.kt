package com.itroad.movies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itroad.movies.app.common.BaseViewModel
import com.itroad.movies.app.common.Resource
import com.itroad.movies.app.common.asResource
import com.itroad.movies.data.models.detail.ResponseDetail
import com.itroad.movies.data.models.movies.ResponseMovie
import com.itroad.movies.data.repositories.MoviesRepository

class MoviesViewModel  @javax.inject.Inject constructor(private val repository: MoviesRepository) :
    BaseViewModel() {


    private val _responseGetMovies = MutableLiveData<Resource<ResponseMovie>?>()
    val responseGetMovies: LiveData<Resource<ResponseMovie>?> = _responseGetMovies

    private val _responseGetMoviesDetail = MutableLiveData<Resource<ResponseDetail>?>()
    val responseGetMoviesDetail: LiveData<Resource<ResponseDetail>?> = _responseGetMoviesDetail



    fun getMovies( ) =uiCoroutine {
        _responseGetMovies.value=Resource.Loading
        val resultCallExample = repository.getMovies()

        _responseGetMovies.value=resultCallExample.asResource()

    }

    fun getMoviesDetail(id_movies:Int) =uiCoroutine {
        _responseGetMoviesDetail.value=Resource.Loading
        val resultCallExample = repository.getDetailMovies(id_movies)

        _responseGetMoviesDetail.value=resultCallExample.asResource()

    }




}



