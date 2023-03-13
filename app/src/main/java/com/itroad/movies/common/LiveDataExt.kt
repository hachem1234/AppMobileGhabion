
package com.itroad.movies.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.itroad.movies.app.common.Resource
import com.myinwi.ma.app.common.event.Event
import com.myinwi.ma.app.common.event.EventObserver

/*
LiveData is a data holder class that can be observed within a given lifecycle.
 */
fun <T> LiveData<Resource<T>>.valueOrNull(): T? {
    val currentValue = value
    if (currentValue is Resource.Success) return currentValue.data
    return null
}
/*
function to observe a LiveData object to reflect the changes back to the views usin Observer
*/
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    liveData.observe(this, Observer { it?.let { t -> body(t) } })
}
/*
function to observe a LiveData object to reflect the changes back to the views usin EventObserver
*/
fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, body: (T) -> Unit = {}) {
    liveData.observe(this, EventObserver { it?.let { t -> body(t) } })
}
/*
LiveData which publicly exposes setValue(T) and postValue(T) method "mutable"
 */
fun <T> MutableLiveData<T>.toImmutableLiveData() = this as LiveData<T>
