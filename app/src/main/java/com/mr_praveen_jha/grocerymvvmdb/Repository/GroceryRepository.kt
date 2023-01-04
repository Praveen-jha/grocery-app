package com.mr_praveen_jha.grocerymvvmdb.Repository

import com.mr_praveen_jha.grocerymvvmdb.Database.GroceryDatabase
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems

class GroceryRepository(private val db : GroceryDatabase) {

    suspend fun insert(items: GroceryItems) = db.getGroceryDao().insert(items)

    suspend fun delete(items: GroceryItems) = db.getGroceryDao().delete(items)

      fun getAllItems() = db.getGroceryDao().getAllGroceryItems()
}