package com.example.zivametest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zivametest.dao.ShopDao
import com.example.zivametest.App
import com.example.zivametest.model.Shop

@Database(
    entities = [Shop :: class],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getShopDao(): ShopDao


    companion object{
        const val DB_NAME = "shop.db"
        const val VERSION = 1

        private val instance: AppDatabase by lazy { create( App.instance) }

        @Synchronized
        internal fun getInstance(): AppDatabase {
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }


    }


}