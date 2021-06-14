package com.example.trilogicafe.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trilogicafe.R
import com.example.trilogicafe.listener.IDrinkLoadListener
import com.example.trilogicafe.listener.IUserLoadListener
import com.example.trilogicafe.model.DrinkModel
import com.example.trilogicafe.model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), IUserLoadListener {
    lateinit var userLoadListener: IUserLoadListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        loadUserFromFirebase()
    }

    private fun loadUserFromFirebase() {
        val userModels : MutableList<UserModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("user")
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    userLoadListener.onLoadUserFailed(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        for (drinkSnapshot in snapshot.children){
                            val userModel = drinkSnapshot.getValue(UserModel::class.java)
                            userModel!!.key = drinkSnapshot.key
                            userModels.add(userModel)
                        }
                        userLoadListener.onLoadUserSuccess(userModels)
                    }
                    else
                        userLoadListener.onLoadUserFailed("!!!!!")
                }

            })

    }


    override fun onLoadUserSuccess(userModeList: List<UserModel>) {
        til_name.hint=userModeList[0].name.toString()
        til_address.hint=userModeList[0].addresss.toString()
        til_number.hint=userModeList[0].addresss.toString()

    }

    override fun onLoadUserFailed(message: String) {
        Snackbar.make(mainLayout, message!!, Snackbar.LENGTH_LONG).show()
    }
}