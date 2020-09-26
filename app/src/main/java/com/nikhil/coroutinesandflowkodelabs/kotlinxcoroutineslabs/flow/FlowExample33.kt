package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * [catch] and [onCompletion]
 */
private fun simple(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    simple()
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed exceptionally")
            }
        }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}