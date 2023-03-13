
package com.itroad.movies.app.data.net

import okhttp3.ResponseBody
/**
 * Convert responsebody to string or print exception error
 */
fun ResponseBody.stringOrEmpty(): String {
    return try {
        this.string()
    } catch (e: Exception) {
        "exception occured" + e.message
    }
}
