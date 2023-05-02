package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {

    var items = listOf<Item>(
        Item(0.05, 0, "item 1"),
        Item(0.1, 0, "item 2"),
        Item(0.15, 0, "item 3"),
        Item(0.2, 0, "item 4"),
        Item(0.25, 0, "item 5"),
        Item(0.3, 0, "item 6"),
        Item(0.35, 0, "item 7"),
        Item(0.4, 0, "item 8"),
        Item(0.45, 0, "item 9"),
        Item(0.5, 0, "item 10"),
        Item(0.55, 0, "item 11"),
        Item(0.6, 0, "item 12"),
    )

    private val _numOfKush = MutableLiveData(0.0)
    val numOfKush: LiveData<Double>
        get() = _numOfKush

    private var _totalMultiplication = 0.0
    val totalMultiplication: Double
        get() = _totalMultiplication

    private val _index = MutableLiveData(0)
    val index:LiveData<Int>
        get() = _index

    fun addKush(multiplier : Double){
        val currentKushCount = _numOfKush.value ?: 0.0
        _numOfKush.value = currentKushCount.plus(1 + (1 * multiplier))
    }

    fun calculateMultiplier(){

    }

}