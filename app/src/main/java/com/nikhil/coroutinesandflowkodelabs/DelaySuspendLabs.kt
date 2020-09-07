package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class DelaySuspendLabs {

    fun example1(): Unit {
        GlobalScope.launch {
            /**
             * [delay] is not a normal function, it is a [suspend] function
             * It is indicated by the Gutter icon on the left
             */
            delay(3000L)
        }
    }

    fun example2(): Unit {
//        Will not compile
//        delay(3000L)
    }

    fun example3(): Unit {
        GlobalScope.launch {
            /**
             * Code written in this fashion will take around 6 seconds in total
             * because 2 suspend functions are called one after another, their [delay] times get added
             */
            Timber.d("Time before network call: ${System.currentTimeMillis()}")
            val networkCallResponse = doNetworkCall()
            val networkCallResponse2 = doNetworkCall2()
            Timber.d("Time after network call: ${System.currentTimeMillis()}")
            Timber.d("Response 1: $networkCallResponse")
            Timber.d("Response 2: $networkCallResponse2")
        }
    }

    /**
     * The [suspend] function can only be called in:
     * 1. another suspend function
     * 2. inside of a Coroutine
     */
    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "Dummy API response"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "Dummy API response 2"
    }
}