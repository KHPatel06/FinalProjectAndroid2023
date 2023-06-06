package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor
import kotlin.time.Duration.Companion.seconds

class ItemViewModel: ViewModel() {

    val items = listOf(
        Item(0.5, MutableLiveData(0), "Cookie", "Eating it makes you feel like clicking more Kush's", MutableLiveData(100.0)),
        Item(1.0, MutableLiveData(0), "Ghost Energy", "You feel energized, you should click more Kush's", MutableLiveData(1000.0)),
        Item(1.5, MutableLiveData(0), "Gaming chair", "Just makes you better, click away", MutableLiveData(1500.0)),
        Item(2.0, MutableLiveData(0), "Kush Farm", "Farms boost how many Kush's you make per click", MutableLiveData(2000.0)),
        Item(2.5, MutableLiveData(0), "Motorized Mouse", "Mechanically increases the amount of Kush's you get per click", MutableLiveData(10000.0)),
        Item(3.0, MutableLiveData(0), "Nathan", "I just felt like putting Nathan in here", MutableLiveData(15000.0)),
        Item(3.5, MutableLiveData(0), "Kush Express Bank", "Takes your Kush's and puts interest on them over time", MutableLiveData(18000.0)),
        Item(4.0, MutableLiveData(0), "Wizard", "He'll give you more Kush with his magic and whatnot", MutableLiveData(20000.0)),
        Item(4.5, MutableLiveData(0), "Kush Inc", "A corporate machine that massively increases your Kush income", MutableLiveData(25000.0)),
        Item(5.0, MutableLiveData(0), "Magic TI-84 plus ", "Does the math and gives you clicks once you press enter",MutableLiveData(28000.0)),
        Item(10.0, MutableLiveData(0), "Time Machine", "Go back in time and get Kush's before they were even born", MutableLiveData(31000.0)),
        Item(1000.0, MutableLiveData(0), "Kush Empire ", "Rule the world with the insane click power this gives you", MutableLiveData(1000000.0)),
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
