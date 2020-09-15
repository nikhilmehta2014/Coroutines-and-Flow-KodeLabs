package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * [Channel], [Channel.send], [Channel.receive]
 *
 * Channel is a non-blocking primitive for communication between a sender (via [SendChannel]) and a receiver (via [ReceiveChannel]).
 * Conceptually, a channel is similar to Java's [BlockingQueue][java.util.concurrent.BlockingQueue],
 * but it has suspending operations instead of blocking ones and can be [closed][SendChannel.close].
 */
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
    repeat(5) { println(channel.receive()) }
    println("Done!")
}