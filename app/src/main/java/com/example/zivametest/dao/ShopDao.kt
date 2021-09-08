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

      @Query("SELECT * FROM 'Shop' WHERE uid == :matchId")
      fun getShop(matchId: Int): Shop

      @Query("DELETE FROM `Shop`")
      abstract fun deleteShop()
}