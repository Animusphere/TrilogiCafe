package com.example.trilogicafe.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trilogicafe.R
import com.example.trilogicafe.adapter.MyCartAdapter
import com.example.trilogicafe.eventbus.UpdateCartEvent
import com.example.trilogicafe.listener.ICartLoadListener
import com.example.trilogicafe.model.CartModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_chart.mainLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CartActivity : AppCompatActivity(), ICartLoadListener {

    var cartLoadListener:ICartLoadListener?=null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().hasSubscriberForEvent(UpdateCartEvent::class.java))
            EventBus.getDefault().removeStickyEvent(UpdateCartEvent::class.java)
        EventBus.getDefault().unregister(this)

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun onUpdateCartEvent(event: UpdateCartEvent)
    {
        loadCartFromFirebase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        init()
        loadCartFromFirebase()
    }

    private fun loadCartFromFirebase() {
        val cartModels : MutableList<CartModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("Cart")
            .child("UNIQUE_USER_ID")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    cartLoadListener!!.onLoadCartFailed(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (cartSnapshot in snapshot.children)
                    {
                        val cartModel = cartSnapshot.getValue(CartModel::class.java)
                        cartModel!!.key = cartSnapshot.key
                        cartModels.add(cartModel)
                    }
                    cartLoadListener!!.onLoadCartSuccess(cartModels)
                }

            })
    }

    private fun init(){
        cartLoadListener = this
        val layoutManger = LinearLayoutManager(this)
        recycler_cart!!.layoutManager = layoutManger
        recycler_cart!!.addItemDecoration(DividerItemDecoration(this,layoutManger.orientation))
        btnback!!.setOnClickListener{finish()}

    }

    override fun onLoadCartSuccess(cartModeList: List<CartModel>) {
        var sum = 0.0
        for (cartModel in cartModeList!!){
            sum+=cartModel!!.totalPrice
        }
        txtTotal.text = StringBuilder("Rp ").append(sum)
        val adapter = MyCartAdapter(this,cartModeList)
        recycler_cart!!.adapter = adapter
    }

    override fun onLoadCartFailed(message: String) {
        Snackbar.make(mainLayout, message!!, Snackbar.LENGTH_LONG).show()
    }
}