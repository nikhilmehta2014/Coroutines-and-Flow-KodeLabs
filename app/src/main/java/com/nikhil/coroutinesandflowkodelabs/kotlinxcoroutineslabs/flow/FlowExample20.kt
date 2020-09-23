package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * [zip]
 */
fun main() = runBlocking<Unit> {
    /**
     * Note that [zip] fun will print the combinations of the minimum of the 2 data types i.e. [Int] and [String] in this example
     */
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three") // strings
    nums.zip(strs) { a, b ->
        "$a -> $b"  // compose a single string
    }
        .collect {
            println(it) // collect and print
        }
}