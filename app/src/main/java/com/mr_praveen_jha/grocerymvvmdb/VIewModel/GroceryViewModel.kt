package com.mr_praveen_jha.grocerymvvmdb.VIewModel


import androidx.lifecycle.ViewModel
import com.mr_praveen_jha.grocerymvvmdb.Entity.GroceryItems
import com.mr_praveen_jha.grocerymvvmdb.Repository.GroceryRepository
import kotlinx.coroutines.*

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert(items: GroceryItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: GroceryItems) = GlobalScope.launch {
        repository.delete(items)
    }

     fun getAllGroceryItems() = repository.getAllItems()

//    private var list = MutableLiveData<List<GroceryItems>> ()
//    val groceryList: LiveData<List<GroceryItems>> get() = list

//    init {
//      val coroutine =   CoroutineScope(Dispatchers.Main).async {
//           repository.getAllItems()
//        }
//        viewModelScope.launch {
//            list.postValue(coroutine.await().value)
//        }
//    }
}