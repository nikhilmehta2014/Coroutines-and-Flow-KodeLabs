package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * [reduce]
 */
fun main() = runBlocking<Unit> {
    val sum = (1..5)
        .asFlow()
        .map {
            it * it
        } // squares of numbers from 1 to 5
        .reduce { a, b ->
            a + b
        } // sum them (terminal operator)
    println(sum)
}