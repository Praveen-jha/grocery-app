package com.mr_praveen_jha.grocerymvvmdb.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "grocery_items")
data class GroceryItems(

    @NotNull
    @ColumnInfo(name = "itemName")
    var itemName : String,

    @NotNull
    @ColumnInfo(name = "itemQuantity")
    var itemQuantity : Int,

    @NotNull
    @ColumnInfo(name = "itemPrice")
    var itemPrice : Int,

    @NotNull
    @PrimaryKey(autoGenerate = true)
var id : Int

)