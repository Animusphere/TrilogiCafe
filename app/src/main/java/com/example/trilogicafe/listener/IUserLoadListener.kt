package com.example.trilogicafe.listener

import com.example.trilogicafe.model.UserModel


interface IUserLoadListener {
        fun onLoadUserSuccess(cartModeList: List<UserModel>)
        fun onLoadUserFailed(message:String)

}