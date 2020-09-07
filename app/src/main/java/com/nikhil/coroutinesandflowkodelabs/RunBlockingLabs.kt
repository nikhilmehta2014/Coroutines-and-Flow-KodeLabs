package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.*
import timber.log.Timber

class RunBlockingLabs {

    /**
     * [runBlocking]
     * Runs a new coroutine and **blocks** the current thread _interruptibly_ until its completion.
     * This function should not be used from a coroutine.
     *
     * The difference between [GlobalScope.launch] and [runBlocking] is that former will not block the main thread but the latter will do.
     *
     * Why would I block my main thread?
     * 1. We can run [suspend] functions from [runBlocking] and that is not possible from main thread.
     * 2. Unit Testing
     *
     * Note: The code inside [runBlocking] will run on the main thread and it will be _synchronous_
     */
    fun example1(): Unit {
        Timber.d("runBlocking: Before runBlocking")
        runBlocking {
            Timber.d("runBlocking: Start of  runBlocking")
            delay(3000L)
            Timber.d("runBlocking: End of  runBlocking")
        }
        Timber.d("runBlocking: After runBlocking")
    }

    /**
     * We can also run multiple [launch] Coroutines from inside [runBlocking]
     * Those [launch] code blocks will run asynchronously to the Main thread.
     *
     * Example:-
     * In the below example, "Finished IO Coroutine 1" and "Finished IO Coroutine 2" will print at the same time i.e. after 3 seconds and
     * not in the sequence that "Finished IO Coroutine 1" will print after 3 seconds and "Finished IO Coroutine 2" will print after 6 seconds
     * This _proves_ that [launch] code blocks run _asynchronously_
     */
    fun example2(): Unit {
        Timber.d("runBlocking 2: Before runBlocking")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Timber.d("Finished IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Timber.d("Finished IO Coroutine 2")
            }
        }
        Timber.d("runBlocking 2: After runBlocking")
    }
}