package com.example.finalprojectandroid2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {

    private val _numOfKush = MutableLiveData(0)
    val numOfKush: LiveData<Int>
        get() = _numOfKush

    private var _totalMultiplication = 0
    val totalMultiplication: Int
        get() = _totalMultiplication

    fun addKush(){
        _numOfKush.value?.plus(1)
    }

}