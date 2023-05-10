package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {

    var items = listOf(
        Item(0.05, 0, "item 1", "Does stuff"),
        Item(0.1, 0, "item 2", "Does stuff"),
        Item(0.15, 0, "item 3", "Does stuff"),
        Item(0.2, 0, "item 4", "Does stuff"),
        Item(0.25, 0, "item 5", "Does stuff"),
        Item(0.3, 0, "item 6", "Does stuff"),
        Item(0.35, 0, "item 7", "Does stuff"),
        Item(0.4, 0, "item 8", "Does stuff"),
        Item(0.45, 0, "item 9", "Does stuff"),
        Item(0.5, 0, "item 10", "Does stuff"),
        Item(0.55, 0, "item 11", "Does stuff"),
        Item(0.6, 0, "item 12", "Does stuff"),
    )

    private val _numOfKush = MutableLiveData(0.0)
    val numOfKush: LiveData<Double>
        get() = _numOfKush

    private var _totalMultiplication = MutableLiveData(0.0)
    val totalMultiplication: LiveData<Double>
        get() = _totalMultiplication

    fun addKush(multiplier : Double){
        val currentKushCount = _numOfKush.value ?: 0.0
        _numOfKush.value = currentKushCount.plus(1 + (1 * multiplier))
    }

    fun calculateMultiplier(position: Int){
        val currentMultiplication = _totalMultiplication.value ?: 0.0
        _totalMultiplication.value = currentMultiplication.plus(items[position].kushMultiplier)
    }
}