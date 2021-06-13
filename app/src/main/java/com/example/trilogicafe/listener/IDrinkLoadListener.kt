package com.example.trilogicafe.listener

import com.example.trilogicafe.model.DrinkModel

interface IDrinkLoadListener {
    fun onDrinkLoadSuccess(drinkModeList:List<DrinkModel>?)
    fun onDrinkLoadFailed(message:String?)
}