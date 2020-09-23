package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

/**
 * [collectLatest]
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
            .collectLatest { value -> // cancel & restart on the latest value
                println("Collecting $value")
                /**
                 * Note that collect [delay] is greater than emission [delay]
                 * If you equal both [delay]s, both [print] values will be printed for every value.
                 */
                delay(300) // pretend we are processing it for 300 ms
                println("Done $value")
            }
    }
    println("Collected in $time ms")
}