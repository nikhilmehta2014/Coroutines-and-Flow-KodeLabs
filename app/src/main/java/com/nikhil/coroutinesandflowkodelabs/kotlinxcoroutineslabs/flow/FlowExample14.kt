package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * This program will crash:
 * [java.lang.IllegalStateException]: Flow invariant is violated
 * Flow was collected in [BlockingCoroutine{Active}@5f649778, BlockingEventLoop@64b2d769],
 * but emission happened in [DispatchedCoroutine{Active}@7c034389, Dispatchers.Default].
 */
private fun simple(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun main() = runBlocking<Unit> {
    simple().collect { value ->
        println(value)
    }
}