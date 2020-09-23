package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

/**
 * [buffer]
 */
private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simple()
            .buffer() // buffer emissions, don't wait
            .collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                println(value)
            }
    }
    println("Collected in $time ms")
}