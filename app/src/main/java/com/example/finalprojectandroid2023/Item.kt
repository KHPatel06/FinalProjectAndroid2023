package com.example.finalprojectandroid2023

import androidx.lifecycle.MutableLiveData

class Item(var kushMultiplier: Double, var quantity: MutableLiveData<Int>, var itemName: String, var desc: String, var price: MutableLiveData<Double>)