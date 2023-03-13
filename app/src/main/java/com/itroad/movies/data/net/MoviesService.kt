package com.itroad.movies.app.data.net

import com.itroad.movies.app.common.Constants
import com.itroad.movies.data.models.detail.ResponseDetail
import com.itroad.movies.data.models.movies.ResponseMovie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface MoviesService {


  @GET(Constants.GET_MOVIES)
  suspend fun getMovies(
    @Query(Constants.KEY) key: String,
  ) : ResponseMovie

  @GET
  suspend fun getDetailMovies(
    @Url url: String,
    @Query(Constants.KEY) key: String
  ) : ResponseDetail
}
