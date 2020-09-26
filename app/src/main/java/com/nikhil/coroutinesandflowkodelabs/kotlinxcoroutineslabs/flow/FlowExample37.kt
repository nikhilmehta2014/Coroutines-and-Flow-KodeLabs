package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * [CoroutineScope.cancel]
 * Cancels this scope, including its job and all its children with an optional cancellation [cause].
 */
fun foo(): Flow<Int> = flow {
    for (i in 1..15) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    foo().collect { value ->
        if (value == 3)
            cancel()
        if(isActive){       // If we do not use [isActive] here, "3" will also print.
            println(value)
        }
    }
}