package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * [launchIn]
 * Terminal flow operator that [launches][launch] the [collection][collect] of the given flow in the [scope] (@this in this example).
 * It is a shorthand for `scope.launch { flow.collect() }`.
 */

// Imitate a flow of events
private fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking<Unit> {
    events()
        .onEach { event -> println("Event: $event in ${Thread.currentThread().name}") }
        .launchIn(this) // <--- Launching the flow in a separate coroutine
    println("Done")
}