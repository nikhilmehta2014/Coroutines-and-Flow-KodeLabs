package com.nikhil.coroutinesandflowkodelabs.kotlinxcoroutineslabs.flow

private fun simple(): List<Int> = listOf(1, 2, 3)

fun main() {
    simple().forEach { value ->
        println(value)
    }
}