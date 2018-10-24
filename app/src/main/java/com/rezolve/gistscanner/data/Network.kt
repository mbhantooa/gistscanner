package com.rezolve.gistscanner.data

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

object Network {
    private fun defaultError(t: Throwable) {
        t.printStackTrace()
    }

    fun <T> request(call: Deferred<T>, callback: NetworkCallback<T>) {
        request(call, callback.success, callback.error)
    }

    private fun <T> request(call: Deferred<T>,
                            onSuccess: ((T) -> Unit)?,
                            onError: ((Throwable) -> Unit)?) {

        GlobalScope.launch {
            try {
                onSuccess?.invoke(call.await())
            } catch (httpException: HttpException) {
                defaultError(httpException)
            } catch (t: Throwable) {
                onError?.invoke((t))
            }
        }
    }
}

open class NetworkCallback<T> {
    var success: ((T) -> Unit)? = null
    var error: ((Throwable) -> Unit)? = null

}