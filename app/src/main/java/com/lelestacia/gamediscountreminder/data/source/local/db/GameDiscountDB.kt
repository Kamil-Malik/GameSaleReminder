package com.lelestacia.gamediscountreminder.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lelestacia.gamediscountreminder.data.source.local.dao.StoreDao
import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity

@Database(entities = [StoreEntity::class], version = 1, exportSchema = true)
abstract class GameDiscountDB : RoomDatabase() {

    abstract fun storeDao(): StoreDao
}