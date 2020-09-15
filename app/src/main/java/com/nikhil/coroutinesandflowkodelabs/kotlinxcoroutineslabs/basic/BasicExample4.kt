package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * [Job.join]
 */
fun main() = runBlocking {
    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // wait until child coroutine completes
}