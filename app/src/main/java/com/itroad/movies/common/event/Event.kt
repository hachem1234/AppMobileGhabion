/**
 * Created by Manar Amine on 21/09/2021.
 * Mobiblanc
 * manar@mobiblanc.com
 */
package com.myinwi.ma.app.common.event

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}
