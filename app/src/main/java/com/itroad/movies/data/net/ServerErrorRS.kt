/**
 * Created by Manar Amine on 21/09/2021.
 * Mobiblanc
 * manar@mobiblanc.com
 */
package com.itroad.movies.data.net

import com.google.gson.annotations.SerializedName

/**
 * Data class for server error serialization
 */
data class ServerErrorRS(
    @SerializedName("status")
    val code: String?,
    @SerializedName("detail")
    val description: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("errorKey")
    val errorKey: String? = "",
    @SerializedName("code")
    val codeGLX: String? = "",
    @SerializedName("message")
    val message: String? = "",

    )
