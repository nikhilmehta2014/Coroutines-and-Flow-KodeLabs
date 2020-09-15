package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.coroutines.CoroutineContext

/**
 * [CoroutineContext.cancelChildren]
 *
 * Note that the order is not consistent, as you can see from the output.
 */
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val numbers = produceNumbers() // produces integers from 1 and on
    val squares = square(numbers) // squares integers
    repeat(5) {
        println(squares.receive()) // print first five
    }
    println("Done!") // we are done
    coroutineContext.cancelChildren() // cancel children coroutines
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) {
        println("x is $x")
        send(x++) // infinite stream of integers starting from 1
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) {
        println("x*x is ${x * x}")
        send(x * x)
    }
}

/**
 * Output:
x is 1
x*x is 1
x is 2
x is 3
1
x*x is 4
x*x is 9
4
9
x is 4
x*x is 16
x is 5
x is 6
16
x*x is 25
x*x is 36
25
Done!
x is 7
 */
