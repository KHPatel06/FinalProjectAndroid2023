package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor

class ItemViewModel: ViewModel() {

    var items = listOf(
        Item(0.5, MutableLiveData(0), "item 1", "Does stuff", MutableLiveData(100.0)),
        Item(1.0, MutableLiveData(0), "item 2", "Does stuff", MutableLiveData(1000.0)),
        Item(1.5, MutableLiveData(0), "item 3", "Does stuff", MutableLiveData(1100.0)),
        Item(2.0, MutableLiveData(0), "item 4", "Does stuff", MutableLiveData(2000.0)),
        Item(2.5, MutableLiveData(0), "item 5", "Does stuff", MutableLiveData(10000.0)),
        Item(3.0, MutableLiveData(0), "item 6", "Does stuff", MutableLiveData(15000.0)),
        Item(3.5, MutableLiveData(0), "item 7", "Does stuff", MutableLiveData(18000.0)),
        Item(4.0, MutableLiveData(0), "item 8", "Does stuff", MutableLiveData(20000.0)),
        Item(4.5, MutableLiveData(0), "item 9", "Does stuff", MutableLiveData(25000.0)),
        Item(5.0, MutableLiveData(0), "item 10", "Does stuff",MutableLiveData(28000.0)),
        Item(5.5, MutableLiveData(0), "item 11", "Does stuff", MutableLiveData(31000.0)),
        Item(10.0, MutableLiveData(0), "item 12", "Does stuff", MutableLiveData(1000000.0)),
    )

    private val _numOfKush = MutableLiveData(0.00)
    val numOfKush: LiveData<Double>
        get() = _numOfKush

    private val _totalMultiplication = MutableLiveData(0.0)
    val totalMultiplication: LiveData<Double>
        get() = _totalMultiplication

    fun addKush(){
        val currentKushCount = _numOfKush.value ?: 0.00
        _numOfKush.value = currentKushCount.plus(1 + (1 * totalMultiplication.value!!))
    }

    fun buyItem(position: Int){
        _numOfKush.value = _numOfKush.value?.minus(items[position].price.value ?: 0.0)
        val currentMultiplication = _totalMultiplication.value ?: 0.0
        _totalMultiplication.value = currentMultiplication.plus(items[position].kushMultiplier)
        items[position].quantity.value = items[position].quantity.value?.plus(1)
        items[position].price.value = items[position].price.value?.times(1.13)
    }

    fun sellItem(position: Int, amountToSell: Int){
        _numOfKush.value = _numOfKush.value?.plus((items[position].price.value ?: 0.0) / (1.13 * amountToSell))
        _numOfKush.value = _numOfKush.value?.plus(((items[position].price.value ?: 0.0) / 1.13 * amountToSell) * amountToSell)
        items[position].price.value = (items[position].price.value ?: 0.0) / (1.13 * amountToSell)
        val currentMultiplication = _totalMultiplication.value ?: 0.0
        _totalMultiplication.value = currentMultiplication.minus((items[position].kushMultiplier) * amountToSell)
    }
}
