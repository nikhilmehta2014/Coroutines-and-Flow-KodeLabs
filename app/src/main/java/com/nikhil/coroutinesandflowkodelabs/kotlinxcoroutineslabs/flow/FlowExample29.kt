package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * This program will crash
 */
private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    simple()
        .catch { e -> println("Caught $e") } // does not catch downstream exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}