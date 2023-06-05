package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Item(var kushMultiplier: Double = 0.0, var quantity: MutableLiveData<Int> = MutableLiveData(0), var itemName: String = "", var desc: String = "", var price: MutableLiveData<Double> = MutableLiveData(0.0))