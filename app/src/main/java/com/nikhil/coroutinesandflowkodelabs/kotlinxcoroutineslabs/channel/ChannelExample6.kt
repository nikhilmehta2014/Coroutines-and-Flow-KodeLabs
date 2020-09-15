package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * [Channel.cancel]
 */
@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val producer = produceNumbers()
    repeat(5) { launchProcessor(it, producer) }
    delay(950)
    producer.cancel() // cancel producer coroutine and thus kill them all
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(100) // wait 0.1s
    }
}

private fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}