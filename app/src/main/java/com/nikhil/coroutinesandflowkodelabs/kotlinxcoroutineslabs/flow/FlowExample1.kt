package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

/**
 * [listOf]
 */
fun main() {
    simple().forEach { value ->
        println(value)
    }
}

private fun simple(): List<Int> = listOf(1, 2, 3)