package com.example.jobtask

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject

class Repository(val application: Application) {
    lateinit var remoteConfig: FirebaseRemoteConfig
    lateinit var catArray: ArrayList<CategoryModel>
    lateinit var productsArray: ArrayList<ProductsModel>
    val categoriesList = MutableLiveData<List<CategoryModel>>()
    val productsList = MutableLiveData<List<ProductsModel>>()
    val productsName = MutableLiveData<String>()
    val productsPrice = MutableLiveData<String>()
    val productsImage = MutableLiveData<String>()

    fun getCategories() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    catArray = arrayListOf()
                    var s = remoteConfig.getString("categories")
                    var categoryArray: JSONArray
                    categoryArray = JSONArray(s)
                    var categoryObject: JSONObject
                    for (i in 0..categoryArray.length() - 1) {
                        categoryObject = categoryArray.getJSONObject(i)
                        var categoryModel = CategoryModel(
                            categoryObject.getString("id"),
                            categoryObject.getString("name"),
                            categoryObject.getString("image_url")
                        )
                        catArray.add(categoryModel)
                        categoriesList.value = catArray
                    }
                } else {
                    Toast.makeText(
                        application, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    fun getTrendingProducts() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    productsArray = arrayListOf()
                    var s = remoteConfig.getString("trending_products")
                    var productArray: JSONArray
                    productArray = JSONArray(s)
                    var productObject: JSONObject
                    for (i in 0..productArray.length() - 1) {
                        productObject = productArray.getJSONObject(i)
                        var productsModel = ProductsModel(
                            productObject.getString("product_id"),
                            productObject.getString("product_name"),
                            productObject.getString("product_price"),
                            productObject.getString("product_image")
                        )
                        productsArray.add(productsModel)
                        productsList.value = productsArray
                    }
                } else {
                    Toast.makeText(
                        application, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}