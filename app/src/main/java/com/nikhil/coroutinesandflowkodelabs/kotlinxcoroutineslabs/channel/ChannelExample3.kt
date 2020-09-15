package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * [CoroutineScope.produce], [consumeEach]
 */
@ExperimentalCoroutinesApi
fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
}