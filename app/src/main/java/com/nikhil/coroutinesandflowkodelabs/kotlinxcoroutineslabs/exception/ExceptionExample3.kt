package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.exception

import kotlinx.coroutines.*

/**
 * [yield]
 */
fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }
        yield()     // If this "yield" is commented, "Child is cancelled" will not be printed as the control flow will not go inside 'try' block of 'child' Coroutine
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }
    job.join()
}