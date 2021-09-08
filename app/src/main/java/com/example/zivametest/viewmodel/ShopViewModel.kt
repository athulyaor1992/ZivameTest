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

class ShopViewModel(
    val apiService: ApiService = App.api!!,
    val appDatabase: AppDatabase = AppDatabase.getInstance()
): ViewModel(){
    fun getShop() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getShop()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun insertShop(i: Shop) {
        appDatabase.getShopDao().insertShop(i)

    }
    fun getShopCount(): Int {
        return appDatabase.getShopDao().getShopCount()
    }

    fun deleteShop() {
        appDatabase.getShopDao().deleteShop()
    }

}
