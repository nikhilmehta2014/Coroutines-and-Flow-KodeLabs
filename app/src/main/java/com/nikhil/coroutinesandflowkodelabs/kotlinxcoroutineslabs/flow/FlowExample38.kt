package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Note that if we do not use [isActive] in this example, all values will print from 1 to 10.
 */
fun main() = runBlocking<Unit> {
    (1..10).asFlow().collect { value ->
        if (value == 3)
            cancel()
        if(isActive)
            println(value)
    }
}