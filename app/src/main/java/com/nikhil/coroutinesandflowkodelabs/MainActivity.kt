package com.nikhil.coroutinesandflowkodelabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val globalScopeLabs = GlobalScopeLabs()
//        globalScopeLabs.example1()
//        globalScopeLabs.example2()

        val delaySuspendLabs = DelaySuspendLabs()
//        delaySuspendLabs.example1()
//        delaySuspendLabs.example2()
//        delaySuspendLabs.example3()

        val coroutineContextLabs = CoroutineContextLabs()
//        coroutineContextLabs.example1()
//        coroutineContextLabs.example2()

        val runBlockingLabs = RunBlockingLabs()
//        runBlockingLabs.example1()
//        runBlockingLabs.example2()

        val coroutineJobs = CoroutineJobs()
//        coroutineJobs.example1()
//        coroutineJobs.example2()
//        coroutineJobs.example3()
//        coroutineJobs.example4()
//        coroutineJobs.example5()

        val asyncAwait = AsyncAwait()
//        asyncAwait.example1()
//        asyncAwait.example2()
//        asyncAwait.example3()

    }
}