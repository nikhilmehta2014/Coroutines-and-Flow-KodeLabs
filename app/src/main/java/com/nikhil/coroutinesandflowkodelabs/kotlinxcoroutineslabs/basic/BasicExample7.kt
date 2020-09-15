package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * [suspend]
 */
fun main() = runBlocking {
    launch {
        doWorld()
    }
    println("Hello,")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}