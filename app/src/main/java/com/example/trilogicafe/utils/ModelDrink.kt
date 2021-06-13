package com.example.trilogicafe.utils

data class ModelDrink(

    var name: String,

    var body: String,

    var image: String,

    var price: String,

    var isSelected:Boolean


) {

    override fun toString(): String {
        return "ModelDrink(name='$name', image='$image', price=Rp '$price')"
    }
    fun getSelected():Boolean {
        return isSelected
    }
    

}