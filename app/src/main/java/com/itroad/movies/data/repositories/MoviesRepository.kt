package com.itroad.movies.data.repositories

import com.itroad.movies.BuildConfig
import com.itroad.movies.app.common.Constants
import com.itroad.movies.app.common.Result
import com.itroad.movies.app.data.net.RetrofitRunner
import com.itroad.movies.app.data.net.MoviesService
import com.itroad.movies.data.models.detail.ResponseDetail
import com.itroad.movies.data.models.movies.ResponseMovie
import javax.inject.Inject

class MoviesRepository  @Inject constructor(
    private val retrofitRunner: RetrofitRunner,
    private val weatherService: MoviesService
) {





    suspend fun getMovies(): Result<ResponseMovie> {
        return retrofitRunner.executeNetworkCall { weatherService.getMovies(Constants.Data_Key) }
    }

    suspend fun getDetailMovies(id_movies:Int): Result<ResponseDetail> {
        return retrofitRunner.executeNetworkCall { weatherService.getDetailMovies(BuildConfig.BASE_URL+Constants.GET_DETAIL_MOVIES+id_movies,Constants.Data_Key) }
    }



}