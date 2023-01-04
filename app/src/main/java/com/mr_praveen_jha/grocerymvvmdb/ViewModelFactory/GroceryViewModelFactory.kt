package com.mr_praveen_jha.grocerymvvmdb.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mr_praveen_jha.grocerymvvmdb.Repository.GroceryRepository
import com.mr_praveen_jha.grocerymvvmdb.VIewModel.GroceryViewModel

class GroceryViewModelFactory (private val repository: GroceryRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroceryViewModel(repository) as T
    }
}