package com.mr_praveen_jha.grocerymvvmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems
import com.mr_praveen_jha.grocerymvvmdb.R

class GroceryRVAdapter (var list: List<GroceryItems> ,
                     val groceryItemClickedInterface: GroceryItemClickedInterface
                    ) : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "Rs. "+list.get(position).itemPrice.toString()
        val itemTotal : Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. "+itemTotal.toString()
        holder.deleteIV.setOnClickListener {
            groceryItemClickedInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // creating interface for each item clickListener
    interface GroceryItemClickedInterface {
        fun onItemClick(groceryItems: GroceryItems)
    }

    inner class GroceryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmount)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

}