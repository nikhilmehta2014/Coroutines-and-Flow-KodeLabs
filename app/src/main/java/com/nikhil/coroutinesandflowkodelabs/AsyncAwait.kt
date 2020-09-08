package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.system.measureTimeMillis

class AsyncAwait {

    /**
     * If we have several [suspend] functions inside a Coroutine, they are sequential by default, which means
     * that the 1st fun wll be executed first and when it is finished, 2nd will execute.
     *
     * Below example will take ~6 seconds as it is synchronous.
     */
    fun example1() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                val apiResponse1 = networkCall1()
                val apiResponse2 = networkCall2()
                Timber.d("apiResponse1 = $apiResponse1")
                Timber.d("apiResponse2 = $apiResponse2")
            }
            Timber.d("Request took $time ms")
        }
    }

    /**
     * Naive way to solve problem in example1
     *
     * Remember that for "apiResponse1" and "apiResponse2" to print the log, we have to wait for them to finish, otherwise they would return "null"
     * We can do that using [Job.join]
     *
     * Below example will take ~3 seconds as it is asynchronous.
     */
    fun example2() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                var apiResponse1: String? = null
                var apiResponse2: String? = null
                val job1 = launch {
                    apiResponse1 = networkCall1()
                }
                val job2 = launch {
                    apiResponse2 = networkCall2()
                }
                async { }
                job1.join()
                job2.join()
                Timber.d("apiResponse1 = $apiResponse1")
                Timber.d("apiResponse2 = $apiResponse2")
            }
            Timber.d("Request took $time ms")
        }
    }

    /**
     * Proper way to solve issue in example 1
     *
     * We can use [CoroutineScope.async]. It will also start a Coroutine, just like [launch]
     *
     * The difference between [CoroutineScope.async] and [launch] is that the former does not return a [Job], whereas the latter does.
     * Instead, [CoroutineScope.async] returns [Deferred]
     *
     * When we use [CoroutineScope.async], the last line of the Coroutine is returned.
     *
     * When we are using [CoroutineScope.async], we have to use [Deferred.await] to get the result from [CoroutineScope.async]
     *
     * [Deferred.await] - Awaits for completion of this value without blocking a thread and resumes when deferred computation is complete
     *
     * Below example will take ~3 seconds as it is asynchronous.
     */
    fun example3() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                val apiResponse1 = async {
                    networkCall1()
                }
                val apiResponse2 = async {
                    networkCall2()
                }
                Timber.d("apiResponse1 = ${apiResponse1.await()}")
                Timber.d("apiResponse2 = ${apiResponse2.await()}")
            }
            Timber.d("Request took $time ms")
        }
    }

    private suspend fun networkCall1(): String {
        delay(3000L)
        return "API Response 1"
    }

    private suspend fun networkCall2(): String {
        delay(3000L)
        return "API Response 2"
    }
}