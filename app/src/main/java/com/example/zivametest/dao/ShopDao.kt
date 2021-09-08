package com.example.zivametest.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zivametest.model.Shop


@Dao
interface ShopDao {

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertShop(snag: Shop): Long

      @Query("SELECT * FROM 'Shop'")
      fun getShop(): List<Shop>

      @Query("select count(*) as count from 'Shop'")
      fun getShopCount(): Int

      @Query("DELETE FROM `Shop`")
      abstract fun deleteShop()
}