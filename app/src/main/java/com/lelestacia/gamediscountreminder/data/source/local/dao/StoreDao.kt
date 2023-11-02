package com.lelestacia.gamediscountreminder.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStores(stores: List<StoreEntity>)

    @Query("SELECT * FROM store")
    suspend fun getAllStore(): List<StoreEntity>

    @Query("SELECT * FROM store WHERE store_id = :storeId")
    suspend fun getStoreByStoreId(storeId: String): StoreEntity?
}