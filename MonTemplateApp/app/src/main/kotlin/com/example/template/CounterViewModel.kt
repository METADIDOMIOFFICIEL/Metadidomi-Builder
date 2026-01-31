package com.example.template

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val count = mutableIntStateOf(0)

    fun increment() {
        count.intValue++
    }

    fun decrement() {
        count.intValue--
    }
}