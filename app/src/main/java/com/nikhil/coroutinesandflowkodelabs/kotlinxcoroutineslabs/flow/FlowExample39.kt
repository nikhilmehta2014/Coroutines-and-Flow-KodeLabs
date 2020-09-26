package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * [cancellable]
 * Returns a flow which checks cancellation status on each emission and throws the corresponding cancellation cause if flow collector was cancelled.
 * Note that [flow] builder is [cancellable] by default.
 */
fun main() = runBlocking<Unit> {
    (1..5).asFlow().cancellable().collect { value ->
        if (value == 3)
            cancel()
        println(value)
    }
}