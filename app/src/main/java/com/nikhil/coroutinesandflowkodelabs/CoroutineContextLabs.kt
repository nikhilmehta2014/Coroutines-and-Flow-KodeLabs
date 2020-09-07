package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.*
import timber.log.Timber

class CoroutineContextLabs {

    /**
     * Coroutine Context
     * Coroutines are always started in a specific context
     *
     * [GlobalScope.launch] does not give us much control over it.
     *
     * We can pass the [Dispatchers] to [GlobalScope.launch] in the following way:
     */
    fun example1(): Unit {
        GlobalScope.launch(Dispatchers.Main) { }                               // A coroutine dispatcher that is confined to the Main thread operating with UI objects.
        GlobalScope.launch(Dispatchers.IO) { }                                 // offloading blocking IO tasks to a shared pool of threads. Useful for Network and Database operation and reading and writing to a file
        GlobalScope.launch(Dispatchers.Default) { }                            // The default [CoroutineDispatcher] that is used by all standard builders like  [launch][CoroutineScope.launch], [async][CoroutineScope.async], etc. Useful for complex and long running operations that will block the main thread, e.g. sorting a list of 10,000 elements.
        GlobalScope.launch(Dispatchers.Unconfined) { }                         // A coroutine dispatcher that is not confined to any specific thread.
        GlobalScope.launch(newSingleThreadContext("MyThread")) { }      // Creates a coroutine execution context using a single thread with built-in [yield] support
    }

    /**
     * Another advantage of Coroutine is Thread-switching.
     * Use case - Make network call on background thread and publish the result on main thread.
     */
    fun example2(): Unit {
        GlobalScope.launch {
            Timber.d("Starting API call in thread: ${Thread.currentThread().name}")
            val apiResponse = doNetworkCall()
            withContext(Dispatchers.Main) {
                Timber.d("Receiving API response in thread: ${Thread.currentThread().name}")
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "Dummy API response"
    }
}