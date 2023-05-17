package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {

    var items = listOf(
        Item(0.1, 0, "item 1", "Does stuff", 100.0),
        Item(0.15, 0, "item 2", "Does stuff", 1000.0),
        Item(0.2, 0, "item 3", "Does stuff", 1100.0),
        Item(0.25, 0, "item 4", "Does stuff", 2000.0),
        Item(0.3, 0, "item 5", "Does stuff", 10000.0),
        Item(0.35, 0, "item 6", "Does stuff", 15000.0),
        Item(0.4, 0, "item 7", "Does stuff", 18000.0),
        Item(0.45, 0, "item 8", "Does stuff", 20000.0),
        Item(0.5, 0, "item 9", "Does stuff", 25000.0),
        Item(0.55, 0, "item 10", "Does stuff",28000.0),
        Item(0.6, 0, "item 11", "Does stuff", 31000.0),
        Item(10.0, 0, "item 12", "Does stuff", 1000000.0),
    )

    private var _numOfKush = MutableLiveData(0.00)
    val numOfKush: LiveData<Double>
        get() = _numOfKush

    private var _totalMultiplication = MutableLiveData(0.0)
    val totalMultiplication: LiveData<Double>
        get() = _totalMultiplication

    fun addKush(multiplier : Double){
        val currentKushCount = _numOfKush.value ?: 0.00
        _numOfKush.value = currentKushCount.plus(1 + (1 * multiplier))
    }

    fun buyItem(position: Int){
            _numOfKush.value = _numOfKush.value?.minus(items[position].price)
            val currentMultiplication = _totalMultiplication.value ?: 0.0
            _totalMultiplication.value = currentMultiplication.plus(items[position].kushMultiplier)
            items[position].quantity++
    }
}