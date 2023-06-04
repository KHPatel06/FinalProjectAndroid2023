package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor

class ItemViewModel: ViewModel() {

    var items = listOf(
        Item(0.5, 0, "item 1", "Does stuff", 100.0),
        Item(1.0, 0, "item 2", "Does stuff", 1000.0),
        Item(1.5, 0, "item 3", "Does stuff", 1100.0),
        Item(2.0, 0, "item 4", "Does stuff", 2000.0),
        Item(2.5, 0, "item 5", "Does stuff", 10000.0),
        Item(3.0, 0, "item 6", "Does stuff", 15000.0),
        Item(3.5, 0, "item 7", "Does stuff", 18000.0),
        Item(4.0, 0, "item 8", "Does stuff", 20000.0),
        Item(4.5, 0, "item 9", "Does stuff", 25000.0),
        Item(5.0, 0, "item 10", "Does stuff",28000.0),
        Item(5.5, 0, "item 11", "Does stuff", 31000.0),
        Item(10.0, 0, "item 12", "Does stuff", 1000000.0),
    )

    private val _numOfKush = MutableLiveData(0.00)
    val numOfKush: LiveData<Double>
        get() = _numOfKush

    private val _totalMultiplication = MutableLiveData(0.0)
    val totalMultiplication: LiveData<Double>
        get() = _totalMultiplication

    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int>
        get() = _quantity

    private val _price = MutableLiveData(0.0)
    val price: LiveData<Double>
        get() = _price

    fun setPriceData(position: Int){
        _price.value = items[position].price
    }

    fun addKush(){
        val currentKushCount = _numOfKush.value ?: 0.00
        _numOfKush.value = currentKushCount.plus(1 + (1 * totalMultiplication.value!!))
    }

    fun buyItem(position: Int){
            _numOfKush.value = _numOfKush.value?.minus(items[position].price)
            val currentMultiplication = _totalMultiplication.value ?: 0.0
            _totalMultiplication.value = currentMultiplication.plus(items[position].kushMultiplier)
            _quantity.value = _quantity.value?.plus(1)
            _price.value = items[position].price * 1.13
    }

    fun sellItem(position: Int, amountToSell: Int){
        _numOfKush.value = _numOfKush.value?.plus(items[position].price/(1.13 * amountToSell))
        _quantity.value = _quantity.value?.minus(amountToSell)
        _price.value = items[position].price/(1.13 * amountToSell)
        val currentMultiplication = _totalMultiplication.value ?: 0.0
        _totalMultiplication.value = currentMultiplication.minus((items[position].kushMultiplier) * amountToSell)
    }
}