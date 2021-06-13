package com.example.trilogicafe.listener

import com.example.trilogicafe.model.CartModel

interface ICartLoadListener {
    fun onLoadCartSuccess(cartModeList: List<CartModel>)
    fun onLoadCartFailed(message:String)
}