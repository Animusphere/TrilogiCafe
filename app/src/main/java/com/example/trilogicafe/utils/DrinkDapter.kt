package com.example.trilogicafe.utils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.Model
import com.bumptech.glide.request.RequestOptions
import com.example.trilogicafe.R
import kotlinx.android.synthetic.main.item_container_tc.view.*
import kotlin.collections.ArrayList
import com.example.trilogicafe.utils.ModelDrink


class DrinkDapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val TAG: String = "AppDebug"

    private var items: List<ModelDrink> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_container_tc, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(DrinkList: List<ModelDrink>){
        items = DrinkList
    }

    class BlogViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val image = itemView.imageView
        val name = itemView.txtName
        val price = itemView.txtPrice

        fun bind(drinkList:ModelDrink){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.button_back)
                .error(R.drawable.button_back)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(drinkList.image)
                .into(image)
            name.setText(drinkList.name)
            price.setText(drinkList.price)

        }

    }

}