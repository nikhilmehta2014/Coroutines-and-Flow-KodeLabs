package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


private fun simple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    println("Calling simple function...")
    val flow = simple()
    println("Calling collect...")
    flow.collect { value ->
        println(value)
    }
    println("Calling collect again...")
    flow.collect { value ->
        println(value)
    }
}
