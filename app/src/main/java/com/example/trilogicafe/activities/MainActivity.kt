package com.example.trilogicafe.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trilogicafe.R
import com.example.trilogicafe.utils.DataSource
import com.example.trilogicafe.utils.DrinkDapter
import com.example.trilogicafe.utils.ItemDecor
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drinkAdapter: DrinkDapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        drinkAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        drinklist.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = ItemDecor(30)
            addItemDecoration(topSpacingDecorator)
            drinkAdapter = DrinkDapter()
            adapter = drinkAdapter
        }
    }

}