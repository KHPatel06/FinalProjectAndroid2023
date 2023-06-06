package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor
import kotlin.time.Duration.Companion.seconds

class ItemViewModel: ViewModel() {

    var items = listOf(
        Item(0.5, MutableLiveData(0), "Cookie", "Eating it makes you feel like clicking more Kush's", MutableLiveData(100.0)),
        Item(1.0, MutableLiveData(0), "Ghost Energy", "You feel energized, You should click more Kush's", MutableLiveData(1000.0)),
        Item(1.5, MutableLiveData(0), "sdgd", "Does stuff", MutableLiveData(1100.0)),
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
        var previousPrice: Double
        for(i in 1..amountToSell) {
            previousPrice = (items[position].price.value ?: 0.0) / 1.13
            _numOfKush.value = _numOfKush.value?.plus(previousPrice)
            items[position].price.value = (items[position].price.value ?: 0.0) / 1.13
        }
        items[position].quantity.value = items[position].quantity.value?.minus(amountToSell)
        val currentMultiplication = _totalMultiplication.value ?: 0.0
        _totalMultiplication.value = currentMultiplication.minus((items[position].kushMultiplier) * amountToSell)
    }

    fun updateMultAndCountAfterSave(multiplication: Double, kushCount: Double){
        _totalMultiplication.value = multiplication
        _numOfKush.value = kushCount
    }

    fun updateQAndPAfterSave(itemQuantity: Int, itemPrice: Double, positionToChange: Int){
        items[positionToChange].quantity.value = itemQuantity
        items[positionToChange].price.value = itemPrice

    }
}
