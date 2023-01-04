package com.mr_praveen_jha.grocerymvvmdb.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems
import org.jetbrains.annotations.NotNull

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(item : GroceryItems)

    @Delete
    suspend fun delete(item : GroceryItems)


    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems() : LiveData<List<GroceryItems>>
}