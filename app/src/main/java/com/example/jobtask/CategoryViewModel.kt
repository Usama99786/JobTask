package com.example.jobtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    val repository = Repository(application)
    val categoriesArray: LiveData<List<CategoryModel>>
    val productsArray: LiveData<List<ProductsModel>>
   public lateinit var productName: MutableLiveData<String>
    lateinit var productPrice:MutableLiveData<String>
    lateinit var productImage:MutableLiveData<String>

    init {
        this.categoriesArray = repository.categoriesList
        this.productsArray=repository.productsList
    }
  fun getCategories()
  {
     repository.getCategories()
  }
    fun getTrendingProducts()
    {
        repository.getTrendingProducts()
    }
}