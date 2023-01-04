package com.mr_praveen_jha.grocerymvvmdb

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mr_praveen_jha.grocerymvvmdb.Adapter.GroceryRVAdapter
import com.mr_praveen_jha.grocerymvvmdb.Database.GroceryDatabase
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems
import com.mr_praveen_jha.grocerymvvmdb.Repository.GroceryRepository
import com.mr_praveen_jha.grocerymvvmdb.VIewModel.GroceryViewModel
import com.mr_praveen_jha.grocerymvvmdb.ViewModelFactory.GroceryViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), GroceryRVAdapter.GroceryItemClickedInterface {

    lateinit var list: List<GroceryItems>
    lateinit var groceryRVAdapter: GroceryRVAdapter
    lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList<GroceryItems>()
        groceryRVAdapter = GroceryRVAdapter(list, this)
        idRVItems.layoutManager = LinearLayoutManager(this)
        idRVItems.adapter = groceryRVAdapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)


        groceryViewModel.getAllGroceryItems().observe(this@MainActivity, Observer {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        })
        idFBAdd.setOnClickListener {
            openDialog()
        }
    }
    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted", Toast.LENGTH_SHORT).show()
    }

    fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancle)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<EditText>(R.id.idEDItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.idEDItemPrice)
        val itemQunatityEdt = dialog.findViewById<EditText>(R.id.idEDItemQty)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName: String = itemEdt.text.toString()
            val itemPrice: String = itemPriceEdt.text.toString()
            val itemQuantity: String = itemQunatityEdt.text.toString()

            val qty: Int = itemQuantity.toInt()
            val pr: Int = itemPrice.toInt()

            if (itemName.isNotEmpty() && itemPrice.isNotEmpty()) {
                val items = GroceryItems(itemName, qty, pr, 0)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext, "item inserted", Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "please enter all the data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialog.show()
    }
}