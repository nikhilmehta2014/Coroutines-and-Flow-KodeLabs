package com.nikhil.coroutinesandflowkodelabs

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        example1()
        example2()
    }

    /**
     * We have learnt that [GlobalScope.launch] is not ideal is most of the scenarios as it lives till the lifecycle of the App.
     *
     * This example demonstrates why it's not recommended to use [GlobalScope.launch]
     *
     * This example demonstrates that the Coroutine will not finish when the [AppCompatActivity] is finished, but it finishes when the [Application] is finished.
     * This can create _memory leaks_ since the Activity is destroyed but the resources attached to Activity would not be garbage collected since the Coroutine is still running and using those resources.
     */
    private fun example1() {
        btn_second_activity.setOnClickListener {
            GlobalScope.launch {
                while (true) {
                    delay(1000L)
                    Timber.d("Still running...")
                }
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@SecondActivity, ThirdActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    /**
     * Correct version of using the correct scope and eliminating the issue in example1.
     *
     * Use [lifecycleScope]
     */
    private fun example2() {
        btn_second_activity.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Timber.d("Still running...")
                }
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@SecondActivity, ThirdActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    /**
     * Similar to [lifecycleScope], we also have [ViewModel.viewModelScope]
     *
     * Definition:
     * CoroutineScope] tied to this [ViewModel].
     * This scope will be canceled when ViewModel will be cleared, i.e [ViewModel.onCleared] is called
     */

}