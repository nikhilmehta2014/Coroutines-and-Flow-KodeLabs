package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.*
import timber.log.Timber

class CoroutineJobs {

    /**
     * We can wait for the Coroutine to finish using [Job.join]
     *
     * Also, keep in mind that we cannot call [Job.join] from main thread as it's a suspend function, hence use [runBlocking]
     *
     * TODO: Unable to understand the Logs behaviour when I change the values of 'repeat' parameter and 'delay' parameter
     */
    fun example1() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(10) {
                Timber.d("CoroutineJobs - example1() is executing")
                delay(500L)
            }
        }

        runBlocking {
            job.join()
            Timber.d("CoroutineJobs - Main thread is resumed")
        }
    }

    fun example2() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Timber.d("CoroutineJobs - example1() is executing")
                delay(1000L)
            }
        }

        runBlocking {
            delay(2000L)
            job.cancel()
            Timber.d("CoroutineJobs - Main thread is resumed")
        }
    }

    /**
     * [Job.cancel] is not always easy as cancellation is co-operative and Coroutine needs to be setup to be properly cancelled.
     * In the above examples, we are using [delay] hence Coroutines get idle time to listen to the [cancel] method.
     *
     * In the below example, [Job] will still run even after [cancel] is called.
     * Reason is that Coroutine is busy in running fibonacci calculations that it does not check for cancellation call.
     * Also, there is no [delay] function called here so no idle time for Coroutine to check for cancellation call.
     *
     * Example of improper [cancel]:-
     */
    fun example3() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            Timber.d("CoroutineJobs - Starting long running calculation")
            for (i in 30..40) {
                Timber.d("CoroutineJobs - Result for i = $i : ${fib(i)}")
            }
            Timber.d("CoroutineJobs - Ending long running calculation")
        }

        runBlocking {
            delay(1000L)
            job.cancel()
            Timber.d("CoroutineJobs - Cancelled Job!")
        }
    }

    private fun fib(n: Int): Long {
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fib(n - 1) + fib(n - 2)
        }
    }

    /**
     * Proper way to handle it: Use [isActive]
     */
    fun example4() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            Timber.d("CoroutineJobs - Starting long running calculation")
            for (i in 30..40) {
                if (isActive) {
                    Timber.d("CoroutineJobs - Result for i = $i : ${fib(i)}")
                }
            }
            Timber.d("CoroutineJobs - Ending long running calculation")
        }

        runBlocking {
            delay(200L)
            job.cancel()
            Timber.d("CoroutineJobs - Cancelled Job!")
        }
    }

    /**
     * A Practical example of using [cancel] is Network TimeOut.
     * Use [withTimeout]
     *
     * Observation: [withTimeout] is more accurate than [isActive] check
     */
    fun example5() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            Timber.d("CoroutineJobs - Starting long running calculation")
            withTimeout(200L) {
                for (i in 30..40) {
                    if (isActive) {
                        Timber.d("CoroutineJobs - Result for i = $i : ${fib(i)}")
                    }
                }
            }
            Timber.d("CoroutineJobs - Ending long running calculation")
        }
    }
}