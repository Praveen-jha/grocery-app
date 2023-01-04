package com.mr_praveen_jha.grocerymvvmdb.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mr_praveen_jha.grocerymvvmdb.Dao.GroceryDao
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems

@Database(entities = [GroceryItems::class], version = 1)
 abstract class GroceryDatabase : RoomDatabase(){
    abstract fun getGroceryDao() : GroceryDao

    companion object {
        @Volatile
        private var instance: GroceryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GroceryDatabase::class.java,
                "Grocery.db"

            )   // .allowMainThreadQueries()
                .build()

    }
}