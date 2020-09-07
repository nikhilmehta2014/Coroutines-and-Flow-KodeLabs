package com.nikhil.coroutinesandflowkodelabs

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class GlobalScopeLabs {

    fun example1() {
        /**
         * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
         * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
         *
         * This is not the preferred way to define a [Coroutine] in a production application because the scope of this Coroutine will live as long as the Application live.
         *
         * The code we write inside [GlobalScope.launch] will be executed asynchronously on a background thread.
         */
        GlobalScope.launch {
            Timber.d("Coroutine says Hello from thread: ${Thread.currentThread().name}")
        }
        Timber.d(" Hello from thread: ${Thread.currentThread().name}")
    }

    fun example2() {
        /**
         * [delay] - Delays coroutine for a given time without blocking a thread and resumes it after a specified time.
         *
         * Note: It only pause the current Coroutine and not block the whole thread.
         *
         * Also, note that [Thread.sleep] blocks the whole thread and this is the difference between [Thread.sleep] and [delay]
         */
        GlobalScope.launch {
            delay(3000L)
            Timber.d("Coroutine says Hello again from thread: ${Thread.currentThread().name}")
        }
        Timber.d("Hello again from thread: ${Thread.currentThread().name}")
    }

    //3
    /**
     * Note: If the App finishes, all Coroutines will be cancelled.
     * You can demo it by putting a [delay] of 10 seconds and then opening and immediately closing the app.
     */
}