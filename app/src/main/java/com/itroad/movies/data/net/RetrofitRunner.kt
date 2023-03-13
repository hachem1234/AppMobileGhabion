/**
 * Created by Manar Amine on 21/09/2021.
 * Mobiblanc
 * manar@mobiblanc.com
 */
package com.itroad.movies.app.data.net

import android.util.Log
import com.google.gson.Gson
import com.itroad.movies.R
import com.itroad.movies.app.common.AppMessage
import com.itroad.movies.app.common.Constants
import com.itroad.movies.app.common.ErrorDetail
import com.itroad.movies.app.common.Result
import com.itroad.movies.data.net.ServerErrorRS
import com.itroad.movies.util.code
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRunner @Inject constructor(
    private val gson: Gson,
) {

    suspend fun <T> executeNetworkCall(
        request: suspend () -> T,
    ): Result<T> = try {
        var response:T? = null

            response=request()
            Result.Success(response!!)

    } catch (e: HttpException) {
        errorFromException(e, gson)
    } catch (e: JSONException) {
        Log.e("RetrofitRunner", Constants.NETWORK_WITH_STATUS)
        errorFromException(e, Gson())
    } catch (e: Exception) {
        errorFromException(e, gson)
    }

    suspend fun <T> executeNetworkWithStatus(request: suspend () -> Response<T>): Result<Unit> =
        try {
            val response = request()
            var body = response.body()
            code = response.code()
            if (response.code() in 200..300 && response.isSuccessful) {
                Result.Success(Unit)
            } else {
                errorFromException(
                    HttpException(response),
                    gson
                )
            }
        } catch (e: HttpException) {
            errorFromException(e, gson)
        } catch (e: JSONException) {
            Log.e("RetrofitRunner", Constants.NETWORK_WITH_STATUS)
            errorFromException(e, Gson())
        } catch (e: Exception) {
            errorFromException(e, gson)
        }
}

private fun errorFromException(exception: Exception, gson: Gson): Result.Error {
    var message: AppMessage = AppMessage.ResourceMessage(R.string.common_technical_issue)
    // We try to get some information about the error
    var errorDetail: ErrorDetail = ErrorDetail()

    when(exception){
        is HttpException->{
            try {
                val serverErrorStr = exception.response()?.errorBody()?.string()

                if (serverErrorStr != null) {
                    errorDetail.messageServer = serverErrorStr

                    val errorRS = gson.fromJson(serverErrorStr, ServerErrorRS::class.java)
                    errorDetail.keyErreur = errorRS.errorKey

                    if (errorRS.description != null) {
                        message = AppMessage.StringMessage(errorRS.description)
                    }
                    if (!errorRS.codeGLX.isNullOrEmpty() || !errorRS.message.isNullOrEmpty()) {
                        errorDetail.message = errorRS.message
                    }
                } else if (exception.code() != null) {
                    errorDetail.status = exception.code()
                    errorDetail.keyErreur = exception.code().toString()
                }
//            } catch (exception: JSONException) {
//                Log.e("RetrofitRunner", Constants.NETWORK_WITH_STATUS)
//            }
            } catch (e: Exception) {

                Log.i("Exception", e.toString())
            }
        }
        is  UnknownHostException->{
            message = AppMessage.ResourceMessage(R.string.common_check_internet_connection)
        }
    }

    return Result.Error(message, errorDetail)
}
