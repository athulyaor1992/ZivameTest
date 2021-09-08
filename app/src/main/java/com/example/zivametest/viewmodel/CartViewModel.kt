package com.example.zivametest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.zivametest.App
import com.example.zivametest.db.AppDatabase
import com.example.zivametest.model.Shop
import com.example.zivametest.network.ApiService
import com.example.zivametest.util.Resource
import kotlinx.coroutines.Dispatchers

class CartViewModel(
    val appDatabase: AppDatabase = AppDatabase.getInstance()
): ViewModel(){
    fun getCart() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getCartDetails()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun getCartDetails(): List<Shop> {
       return appDatabase.getShopDao().getShop()
    }


    fun deleteShop() {
        appDatabase.getShopDao().deleteShop()
    }

}
